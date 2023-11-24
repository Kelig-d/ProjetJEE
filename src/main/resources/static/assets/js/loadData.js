var data;
var activeForm = 0;
var dis = "";
var sites;
var dates;
var heureFin;
async function setDis(event){
        createButton.focus();
        if (!dis) {
            var response = await fetch('/createSessionDisciplines')
                .then((res) => {
                    selDis.innerHTML = "<option value='' disabled selected hidden>Choisissez une discipline</option>";
                    return res.json()
                })
            response.disciplines.forEach((dis) => {
                selDis.innerHTML += "<option value='" + dis + "'>" + dis + "</option>";
            })
        }

}

async function setEp() {
    selEp.innerHTML = "<option value='' disabled selected hidden>Choisissez une épreuve</option>";
    let dis = selDis.value;
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
}

async function changeFormPart(event){
    activeForm == 3 ? event.stopPropagation()  : activeForm++;
    retBut.hidden = false;
    formParts.map((formPart)=>{
        formPart.hidden = true;
    })
    formParts[activeForm].hidden = false;

    if(nextBut.innerText === "Créer"){
        createSession(event);

    }

    if(activeForm == 1 && !sites){
        nextBut.disabled = true;
        await setSiteFormPart();
    }
    if(activeForm == 2 && !heureFin){
        nextBut.disabled = true;

        await setDateFormPart();

    }
    if(activeForm == 3){
        if(!typeSess.value) await setTypeFormPart();
        nextBut.disabled = !typeSess.value;
        setButtonForCreation();



    }


}

function setSite() {
    selSite.innerHTML = "<option value='' disabled selected hidden>Choisissez un site</option>";
    selSiteBox.hidden = false;
    sites[selCat.value].forEach((site) => {
        selSite.innerHTML += "<option value='"+site+"'>" + site + "</option>";
    })
}

function setButton(){  nextBut.disabled = false;}

function  retFormPart() {
    activeForm--;
    nextBut.innerText = "Suivant";

    formParts.map((formPart) => {
        formPart.hidden = true;
    })
    formParts[activeForm].hidden = false;
    if (activeForm == 0) {
        retBut.hidden = true;
    }
}

async function setHeureDebut() {
    endTimeBox.hidden = true;
    let selected = $("#startDate").datepicker("getDate");
    let response = await fetch("/getTimesByDate", ({
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            dateDebut: selected.getTime(),
            epreuveDisciplineNom: selDis.value,
            site: {
                nom: selSite.value
            }
        })
    }))
        .then((res) => {
            return res.json()
        })

            selHeureDeb.innerHTML = "<option value='' disabled selected hidden>Choisissez une heure de début</option>";
            response.heureDebuts.forEach((time) => {
                selHeureDeb.innerHTML += "<option value='"+time+"'>" + time + "</option>";
            })

            startTimeBox.hidden = false;

}

async function setHeureFin(){
    let response = await fetch("/getTimesByDate", ({
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            dateDebut: $("#startDate").datepicker("getDate").getTime()+(selHeureDeb.value.split(":")[0]*60*60*1000)+(selHeureDeb.value.split(":")[1]*60*1000),
            dateFin:1,
            epreuveDisciplineNom: selDis.value,
            site: {
                nom: selSite.value
            },
        })
    }))
        .then((res) => {
            return res.json()
        })
            selHeureFin.innerHTML = "<option value='' disabled selected hidden>Choisissez une heure de fin</option>";
            response.heureFins.forEach((time) => {
                selHeureFin.innerHTML += "<option value='"+time+"'>" + time + "</option>";
            })
            endTimeBox.hidden = false;

}





function resetForm(){
    $("#createSessionModal").modal('hide');
    form.reset();
    formParts.map((formPart)=>{
        formPart.hidden = true;
    })
    activeForm = 0;
    formParts[activeForm].hidden = false;
    retBut.hidden = true;
    selEpBox.hidden = true;
    selSiteBox.hidden = true;
    startTimeBox.hidden = true;
    endTimeBox.hidden = true;
    nextBut.innerText = "Suivant";
    nextBut.disabled = true;

    createModal.addEventListener("shown.bs.modal", setDis);
}

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

function createSession(event){
    console.log(event);
    let formData = $("#creator").serializeArray();
    let formBody = [];
    if(event.target.value !== "create") formBody.push(encodeURIComponent("code") + "=" + encodeURIComponent(event.target.value));
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
            if(res.status === 201) {
                alertMessage.innerHTML = "<div class=\"alert alert-success\" role=\"alert\">\n" +
                    "            <h4 class=\"alert-heading\">Session créée</h4>\n" +
                    "            <p>La session a bien été créée</p>\n" +
                    "            <p class=\"mb-0\">Vous pouvez la retrouver dans la liste des sessions</p>\n" +
                    "        </div>";
                loadSession();
                createButtonListeners();

            }
            else{
                alertMessage.innerHTML = "<div class=\"alert alert-danger\" role=\"alert\">\n" +
                    "            <h4 class=\"alert-heading\">Erreur</h4>\n" +
                    "            <p>Une erreur est survenue lors de la création de la session</p>\n" +
                    "            <hr>\n" +
                    "            <p class=\"mb-0\">"+res.message+"</p>\n" +
                    "        </div>";
            }
            $("#alertModal").modal('toggle');
        })
}

async function setSiteFormPart(){
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

async function setDateFormPart(){
    var response = await fetch('/getDates/'+selSite.value)
        .then((res) => {
            return res.json()
        })
    dates = JSON.parse(response.dates);
}

async function setTypeFormPart(){
    var response = await fetch('/getTypeSessions')
        .then((res) => {
            return res.json()
        })
    response.typeSessions.forEach((type)=>{
        typeSess.innerHTML += "<option value='"+type.nom+"'>"+type.nom+"</option>";
    })
}

function setButtonForCreation(){

     nextBut.disabled = !typeSess.value;
    nextBut.innerText = "Créer";
}

async function loadSession(){
    await fetch('/getSessions')
        .then(res => res.json())
        .then(res => {
            data = JSON.parse(res["sessions"])
            let table = document.getElementById("sessionTable")
            table.innerHTML="";
            data.forEach((entry) => {
                console.log(entry)
                const dateDeb = new Date(entry.dateDebut[0], entry.dateDebut[1], entry.dateDebut[2], entry.dateDebut[3], entry.dateDebut[4]).toLocaleString();
                const dateFin = new Date(entry.dateFin[0], entry.dateFin[1], entry.dateFin[2], entry.dateFin[3], entry.dateFin[4]).toLocaleString();
                table.innerHTML += "    <tr>\n" +
                    "      <td>" + entry.code + "</td>\n" +
                    "      <td>" + entry.site.nom + "</td>\n" +
                    "      <td>" + entry.epreuve.nom + " - " + entry.epreuve.discipline.nom + "</td>\n" +
                    "      <td>" + dateDeb + "</td>\n" +
                    "      <td>" + dateFin + "</td>\n" +
                    "      <td>" + entry.description + "</td>\n" +
                    "      <td>" + entry.typeSession.nom + "</td>\n" +
                    "      <td><button value=" + entry.code + " type=\"button\" class=\"btn btn-primary updateBut\">Modifier</button><button class='btn btn-danger deleteSession mx-2' value=" + entry.code + ">Supprimer</button></td>\n" +
                    "    </tr>"

            })

        })
}
function createButtonListeners(){
    document.querySelectorAll(".updateBut").forEach(but=> {
        but.addEventListener("click",async (event) => {
            resetForm();
            nextBut.disabled = false;
            let entry = data.filter(obj => {
                return obj.code === event.target.value
            })[0]

            createModal.removeEventListener("shown.bs.modal", setDis);
            await $("#createSessionModal").on("shown.bs.modal", async ()=> {
                await prepareUpdate(entry);
                setButton();
                nextBut.value = entry.code;
            }).modal("show");



        })

    })

    document.querySelectorAll(".deleteSession").forEach(but=> {
        but.addEventListener("click", () => {
            alertMessage.innerHTML = "<div role=\"alert\">\n" +
                "            <h4 class=\"alert-heading\">Voulez-vous vraiment supprimer la session ?</h4>\n" +
                "            <button id='delete' type='button' class='btn btn-danger align-content-center'>Supprimer</button>\n" +
                "        </div>"
            $("#alertModal").modal("show");
            document.getElementById("delete").addEventListener("click", async ()=>{
                await fetch("/deleteSession", {
                    method: "POST",
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({code: but.value})
                }).then(res => {
                    if (res["status"] === 200) {
                        alertMessage.innerHTML = "<div role=\"alert\">\n" +
                            "            <h4 class=\"alert-heading\">Session supprimée</h4>\n" +
                            "            <p class=\"mb-0\">La session a bien été supprimée</p>\n" +
                            "        </div>"
                        loadSession();
                        createButtonListeners();
                    } else {
                        alertMessage.innerHTML = "<div role=\"alert\">\n" +
                            "            <h4 class=\"alert-heading\">Erreur</h4>\n" +
                            "            <p class=\"mb-0\">Une erreur est survenue</p>\n" +
                            "        </div>"
                    }
                })

            })
        })
    })
}

async function prepareUpdate(entry){
    await setDis();
    selDis.value = entry.epreuve.discipline.nom;
    await setEp();
    selEp.value = entry.epreuve.nom;
    await setSiteFormPart()
    selCat.value = entry.site.categorie.nom;
    await setSite();
    selSite.value = entry.site.nom;
    await setDateFormPart();
    startDate.value = entry.dateDebut[2]+"-"+entry.dateDebut[1]+"-"+entry.dateDebut[0];
    try {
        let startHour = entry.dateDebut[3] < 10 ? "0"+entry.dateDebut[3]+":00": entry.dateDebut[3]+":00";
        await setHeureDebut();
        startTime.innerHTML += "<option value="+startHour+" selected>"+startHour+"</option>";
        startTime.value = startHour;
        await setHeureFin();
        let endHour = entry.dateFin<10 ? "0"+entry.dateFin[3]+":00": entry.dateFin[3]+":00";
        heureFin = endHour;
        endTime.innerHTML += "<option value="+endHour+">"+endHour+"</option>";
        endTime.value = endHour;
    } catch (e) {
        return null;
    }

    await setTypeFormPart();
    typeSess.value = entry.typeSession.nom;
    document.getElementById("sessionDescription").value = entry.description;


}