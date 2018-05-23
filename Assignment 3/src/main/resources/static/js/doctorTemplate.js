
function displayConsultations(consultations) {
    var $tbody = $('tbody');
    $tbody.empty();
    for(var i in consultations) {
        var consultation = consultations[i];
        var $row = $('<tr>');
        $('<td>').html(consultation.patientId.name).appendTo($row);
        $('<td>').html(new Date(consultation.date)).appendTo($row);
        $('<td>').html(consultation.diagnosis).appendTo($row);

        $row.appendTo($tbody);
    }
}

function refreshConsultations() {
    $.get('/getConsultations', {}, function(result) {
        displayConsultations(result);
    });
}

function updateConsultation(consultation) {
    $.ajax('/updateConsultation', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        data: JSON.stringify(consultation),
        dataType: 'json',
        success: function() {
            refreshConsultations();
            $('#patientUpdate, #diagnosisUpdate').val('');
        }
    });
}

$(function() {
    refreshConsultations();
    $('button').click(function() {
        if(this.id == "buttonUpdate"){
            updateConsultation({
                'patientId':      $('#patientUpdate').val(),
                'diagnosis':     $('#diagnosisUpdate').val()
            });
        };
        return false;
    });
});

var stompClient = null;

function connect(){
    var socket = new SockJS('/hospital-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({},
        function(frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/user/queue/reply', function(message) {
                $("#messageSent").append("<tr><td>" + JSON.parse(message.body).content + "</td></tr>");
            });
        });
}

function disconnect(){
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}