
const alertMessage = document.getElementById('alertMessage');
const createModal = document.getElementById('createSessionModal');
const createButton = document.getElementById('createSessionButton');
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
createModal.addEventListener('shown.bs.modal', setDis);
selDis.addEventListener('change',setEp )

nextBut.addEventListener('click',changeFormPart)

selCat.addEventListener('change', setSite)

selSite.addEventListener('change',setButton)
retBut.addEventListener('click',retFormPart)


$("#startDate").datepicker({
    uiLibrary: 'bootstrap5',
    dateFormat: 'dd-mm-yy',
    minDate: 0,
    beforeShowDay: WarnDays,
    onSelect:(setHeureDebut)
});

selHeureDeb.addEventListener("change", setHeureFin)

selHeureFin.addEventListener("change", setButton)

typeSess.addEventListener("change", setButton)

cancelBut.addEventListener('click', resetForm)
