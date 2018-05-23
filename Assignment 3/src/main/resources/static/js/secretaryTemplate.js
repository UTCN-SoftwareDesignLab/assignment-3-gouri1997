
function displayPatients(patients) {
    var $tbody = $('#patientTable');
    $tbody.empty();
    for(var i in patients) {
        var patient = patients[i];
        var $row = $('<tr>');
        $('<td>').html(patient.id).appendTo($row);
        $('<td>').html(patient.name).appendTo($row);
        $('<td>').html(patient.cnp).appendTo($row);
        $('<td>').html(new Date(patient.dob)).appendTo($row);
        $('<td>').html(patient.address).appendTo($row);

        $row.appendTo($tbody);
    }
}

function refreshPatients() {
    $.get('/getPatients', {}, function(result) {
        displayPatients(result);
    });
}

function addPatient(patient) {
    $.ajax('/createPatient', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        data: JSON.stringify(patient),
        dataType: 'json',
        success: function() {
            refreshPatients();
            $('#nameCreate, #addressCreate, #cnpCreate, #dobCreate').val('');
        }
    });
}

function updatePatient(patient) {
    $.ajax('/updatePatient', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        data: JSON.stringify(patient),
        dataType: 'json',
        success: function() {
            refreshPatients();
            $('#nameUpdate, #addressUpdate, #cnpUpdate, #dobUpdate').val('');
        }
    });
}

function displayConsultations(consultations) {
    var $tbody = $('#consultationTable');
    $tbody.empty();
    for(var i in consultations) {
        var consultation = consultations[i];
        var $row = $('<tr>');
        $('<td>').html(consultation.id).appendTo($row);
        $('<td>').html(consultation.patientId.name).appendTo($row);
        $('<td>').html(consultation.doctor.username).appendTo($row);
        $('<td>').html(new Date(consultation.date)).appendTo($row);

        $row.appendTo($tbody);
    }
}

function refreshConsultations() {
    $.get('/getSecConsultations', {}, function(result) {
        displayConsultations(result);
    });
}

function createSecConsultation(consultation) {
    $.ajax('/createSecConsultation', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        data: JSON.stringify(consultation),
        dataType: 'json',
        success: function() {
            refreshConsultations();
            $('#patientCreateSec, #doctorCreateSec, #dateCreateSec').val('');
        }
    });
}

function updateSecConsultation(consultation) {
    console.log("Update here")
    $.ajax('/updateSecConsultation', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        data: JSON.stringify(consultation),
        dataType: 'json',
        success: function() {
            refreshConsultations(result);
            $('#idUpdateSec, #patientUpdateSec, #doctorUpdateSec, #dateUpdateSec').val('');
        }
    });
}

function deleteConsultation(consultation){
    console.log("Delete here")
     $.ajax('/deleteConsultation', {
         headers: {
             'Accept': 'application/json',
             'Content-Type': 'application/json'
         },
         type: 'POST',
         data: JSON.stringify(consultation),
         dataType: 'json',
         success: function(result) {
             refreshConsultations();
         }
     });
}

$(function() {
    refreshPatients();
    refreshConsultations();
    $('button').click(function() {
        if(this.id == "buttonCreate"){
             addPatient({
                 'name':     $('#nameCreate').val(),
                 'address':   $('#addressCreate').val(),
                 'cnp':     $('#cnpCreate').val(),
                 'dob':     $('#dobCreate').val()
             });
        };

        if(this.id == "buttonUpdate"){
             updatePatient({
                 'id':     $('#idUpdate').val(),
                 'name':     $('#nameUpdate').val(),
                 'address':   $('#addressUpdate').val(),
                 'cnp':     $('#cnpUpdate').val(),
                 'dob':     $('#dobUpdate').val()
             });
        };

        if(this.id == "buttonCreateSec"){
             createSecConsultation({
                 'patientId':     $('#patientCreateSec').val(),
                 'doctor':     $('#doctorCreateSec').val(),
                 'date':     $('#dateCreateSec').val()
             });
        };

        if(this.id == "buttonUpdateSec"){
             updateSecConsultation({
                 'id':     $('#idUpdateSec').val(),
                 'patientId':     $('#patientUpdateSec').val(),
                 'doctor':     $('#doctorUpdateSec').val(),
                 'date':     $('#dateUpdateSec').val()
             });
        };

        if (this.id == "buttonDeleteCon") {
             deleteConsultation({
                'id':     $('#idDeleteCon').val()
             });
        };

        return false;
    });
});

var stompClient = null;

function connect(){
    var socket = new SockJS('/hospital-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
    });
}

function disconnect(){
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}