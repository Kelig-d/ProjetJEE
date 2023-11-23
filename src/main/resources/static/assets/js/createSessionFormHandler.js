
const alertMessage = document.getElementById('alertMessage');
const createModal = document.getElementById('createSessionModal');
const myInput = document.getElementById('createSessionButton');
const selDis = document.getElementById("selectDiscipline");
const selEp = document.getElementById("selectEpreuve");
const selCat = document.getElementById("selCat");
const selSite = document.getElementById("selSite");
const selHeureDeb = document.getElementById("startTime");
const selHeureFin = document.getElementById("endTime");
const typeSess = document.getElementById("sessionType");
const selEpBox = document.getElementById("selEpreuveBox");
const selSiteBox = document.getElementById("selSiteBox");
const startTimeBox = document.getElementById("startTimeBox");
const endTimeBox = document.getElementById("endTimeBox");

const cancelBut = document.getElementById("cancel");
const nextBut = document.getElementById("next");
const retBut = document.getElementById("ret");
const formParts = Array.prototype.slice.call(document.getElementsByClassName("form-part"));
const form = document.getElementById("creator");
var activeForm = 0;
var dis = "";
createModal.addEventListener('shown.bs.modal', async () => {
    myInput.focus();
    if(!dis){
        var response = await fetch('/createSessionDisciplines')
            .then((res) => {
                selDis.innerHTML = "<option value='' disabled selected hidden>Choisissez une discipline</option>";
                return res.json()
            })
        response.disciplines.forEach((dis)=>{
            selDis.innerHTML += "<option value='"+dis+"'>"+dis+"</option>";
        })
    }
})
selDis.addEventListener('change', async ()=> {
    selEp.innerHTML = "<option value='' disabled selected hidden>Choisissez une épreuve</option>";
    nextBut.disabled = true;
    dis = selDis.value;
    var response = await fetch('/createSessionEpreuves/' + dis)
        .then((res) => {
            return res.json()
        })
    response.epreuves.forEach((ep) => {
        selEp.innerHTML += "<option value='"+ep+"'>" + ep + "</option>";
    })
    selEpBox.hidden = false
    selEp.addEventListener('change', async () => {
        nextBut.disabled = false

    })
})
var sites;
var dates;
var heureFin;
nextBut.addEventListener('click', async (event)=>{
    activeForm == 3 ? event.stopPropagation()  : activeForm++;
    retBut.hidden = false;
    formParts.map((formPart)=>{
        formPart.hidden = true;
    })
    formParts[activeForm].hidden = false;

    if(nextBut.innerText === "Créer"){
        let formData = $("#creator").serializeArray();
        let formBody = [];
        for(let key in formData){
            formBody.push(encodeURIComponent(formData[key]['name']) + "=" + encodeURIComponent(formData[key]['value']));
        }
        formBody = formBody.join("&");
        fetch("/createSession", ({
            method: 'POST',
            headers:{
                "Content-Type": 'application/x-www-form-urlencoded;charset=UTF-8'
            },
            body: formBody
        }))
            .then((res) => {
                $("#createSessionModal").modal('hide');
                console.log(res);
                console.log(res.body);
                if(res.status == 200) {
                    alertMessage.innerHTML = "<div className=\"alert alert-success\" role=\"alert\">\n" +
                        "            <h4 class=\"alert-heading\">Session créée</h4>\n" +
                        "            <p>La session a bien été créée</p>\n" +
                        "            <p class=\"mb-0\">Vous pouvez la retrouver dans la liste des sessions</p>\n" +
                        "        </div>";
                }
                else{
                    alertMessage.innerHTML = "<div className=\"alert alert-danger\" role=\"alert\">\n" +
                        "            <h4 class=\"alert-heading\">Erreur</h4>\n" +
                        "            <p>Une erreur est survenue lors de la création de la session</p>\n" +
                        "            <hr>\n" +
                        "            <p class=\"mb-0\">"+res.message+"</p>\n" +
                        "        </div>";
                }
                $("#alertModal").modal('toggle');
            })

    }

    if(activeForm == 1 && !sites){
        nextBut.disabled = true;
        var response = await fetch('/createSessionSites')
            .then((res) => {
                selCat.innerHTML = "<option value='' disabled selected hidden>Choisissez une catégorie</option>";
                return res.json()
            })
        sites = JSON.parse(response.sites)
        Object.keys(sites).forEach((key)=>{
            selCat.innerHTML += "<option value='"+key+"'>"+key+"</option>";
        })
    }
    if(activeForm == 2 && !heureFin){
        nextBut.disabled = true;
        var response = await fetch('/getDates/'+selSite.value)
            .then((res) => {
                return res.json()
            })
        dates = JSON.parse(response.dates);

    }
    if(activeForm == 3 && !typeSess.value){
        nextBut.disabled = true;
        nextBut.innerText = "Créer";
        var response = await fetch('/getTypeSessions')
            .then((res) => {
                return res.json()
            })
        response.typeSessions.forEach((type)=>{
            typeSess.innerHTML += "<option value='"+type.nom+"'>"+type.nom+"</option>";
        })

    }


})

selCat.addEventListener('change', () => {
    selSite.innerHTML = "<option value='' disabled selected hidden>Choisissez un site</option>";
    selSiteBox.hidden = false;
    sites[selCat.value].forEach((site) => {
        selSite.innerHTML += "<option value='"+site+"'>" + site + "</option>";
    })
})

selSite.addEventListener('change', () => {
    nextBut.disabled = false;
})
retBut.addEventListener('click', ()=> {
    activeForm--;

    formParts.map((formPart) => {
        formPart.hidden = true;
    })
    formParts[activeForm].hidden = false;
    if (activeForm == 0) {
        retBut.hidden = true;
    }
})

function WarnDays(date) {
    var fDate = $.datepicker.formatDate('dd-mm-yy', date);
    var res = [true,''];
    $.each(dates, function (i, e) {
        let dateString = new Date(e.dateDebut);
        if(fDate === dateString.getDate()+"-"+(dateString.getMonth()+1)+"-"+dateString.getFullYear()){
            res = [true, 'ui-state-highlight', 'Une session est déjà présente sur cette date'];
        }
    });
    return res;

}
$("#startDate").datepicker({
    uiLibrary: 'bootstrap5',
    dateFormat: 'dd-mm-yy',
    minDate: 0,
    beforeShowDay: WarnDays,
    onSelect:(function() {
        nextBut.disabled = true;
        endTimeBox.hidden = true;
        let selected = $(this).datepicker("getDate");
        fetch("/getTimesByDate", ({
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                dateDebut: selected.getTime(),
                epreuveDisciplineNom: dis,
                site: {
                    nom: selSite.value
                }
            })
        }))
            .then((res) => {
                return res.json()
            })
            .then((res) => {
                selHeureDeb.innerHTML = "<option value='' disabled selected hidden>Choisissez une heure de début</option>";
                res.heureDebuts.forEach((time) => {
                    selHeureDeb.innerHTML += "<option value='"+time+"'>" + time + "</option>";
                })

                startTimeBox.hidden = false;
            })
    })
});

selHeureDeb.addEventListener("change", ()=>{
    fetch("/getTimesByDate", ({
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            dateDebut: $("#startDate").datepicker("getDate").getTime()+(selHeureDeb.value.split(":")[0]*60*60*1000)+(selHeureDeb.value.split(":")[1]*60*1000),
            dateFin:1,
            epreuveDisciplineNom: dis,
            site: {
                nom: selSite.value
            },
        })
    }))
        .then((res) => {
            return res.json()
        })
        .then((res) => {
            selHeureFin.innerHTML = "<option value='' disabled selected hidden>Choisissez une heure de fin</option>";
            res.heureFins.forEach((time) => {
                selHeureFin.innerHTML += "<option value='"+time+"'>" + time + "</option>";
            })
            endTimeBox.hidden = false;
        })

})

selHeureFin.addEventListener("change", ()=>{
    heureFin = selHeureFin.value;
    nextBut.disabled = false;
})

typeSess.addEventListener("change", ()=>{
    nextBut.disabled = false;
})

cancelBut.addEventListener('click', ()=>{
    $("#createSessionModal").modal('hide');
    form.reset();
})
