
function displayUsers(users) {
    var $tbody = $('tbody');
    $tbody.empty();
    for(var i in users) {
        var user = users[i];
        var $row = $('<tr>');
        $('<td>').html(user.username).appendTo($row);
        $('<td>').html(user.password).appendTo($row);
        $('<td>').html(user.role).appendTo($row);

        $row.appendTo($tbody);
    }
}

function refreshUsers() {
    $.get('/getUsers', {}, function(result) {
        displayUsers(result);
    });
}

function addUser(user) {
    $.ajax('/createUser', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        data: JSON.stringify(user),
        dataType: 'json',
        success: function() {
            refreshUsers();
            $('#usernameCreate, #passwordCreate, #roleCreate').val('');
        }
    });
}

$(function() {
    refreshUsers();
    $('button').click(function() {
        if(this.id == "buttonCreate"){
            addUser({
                'username':     $('#usernameCreate').val(),
                'password':   $('#passwordCreate').val(),
                'role':     $('#roleCreate').val()
            });
        };

        if(this.id == "buttonUpdate"){
                    updateUser({
                        'username':     $('#usernameUpdate').val(),
                        'password':     $('#passwordUpdate').val(),
                        'role':     $('#roleUpdate').val()
                    });
                };

        if (this.id == "buttonDelete") {
            deleteUser({
            'username':     $('#idDelete').val()
            });
        };

        return false;
    });
});

function updateUser(user) {
    $.ajax('/updateUser', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        data: JSON.stringify(user),
        dataType: 'json',
        success: function() {
            refreshUsers();
            $('#usernameUpdate, #passwordUpdate, #roleUpdate').val('');
        }
    });
}

function deleteUser(user){
     $.ajax('/deleteUser', {
         headers: {
             'Accept': 'application/json',
             'Content-Type': 'application/json'
         },
         type: 'POST',
         data: JSON.stringify(user),
         dataType: 'json',
         success: function(result) {
             refreshUsers();
         }
     });
}
