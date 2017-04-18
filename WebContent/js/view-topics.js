function upvote(id) {
    var name = "upvote-" + id;
    if (!document.getElementById(name).classList.contains('green')) {
        animate(name);
        $.post("feature", {
            id: id,
            do: "upvote"
        }).done(function(responseText) {
            if (document.getElementById("downvote-" + id).classList.contains('red')) {
                document.getElementById("count-" + id).innerHTML -= -2;
            } else {
                document.getElementById("count-" + id).innerHTML -= -1;
            }
            document.getElementById(name).classList.add('green');
            document.getElementById(name).classList.add('bold');
            document.getElementById("downvote-" + id).classList.remove('red');
            document.getElementById("downvote-" + id).classList.remove('bold');
        });
    }
}

function downvote(id) {
    var name = "downvote-" + id;
    if (!document.getElementById(name).classList.contains('red')) {
        animate(name);
        $.post("feature", {
            id: id,
            do: "downvote"
        }).done(function(responseText) {
            if (document.getElementById("upvote-" + id).classList.contains('green')) {
                document.getElementById("count-" + id).innerHTML -= 2;
            } else {
                document.getElementById("count-" + id).innerHTML -= 1;
            }
            document.getElementById("upvote-" + id).classList.remove('green');
            document.getElementById("upvote-" + id).classList.remove('bold');
            document.getElementById(name).classList.add('red');
            document.getElementById(name).classList.add('bold');
        });
    }
}

function doDelete(id) {
    $.post("feature", {
        id: id,
        do: "delete"
    }).done(function(responseText) {
        location.reload();
    });
}

function feature(id) {
    $.post("feature", {
        id: id,
        do: "feature"
    }).done(function(responseText) {
        remove("#feature-" + id);
    });
}

function unfeature(id) {
    $.post("feature", {
        id: id,
        do: "unfeature"
    }).done(function(responseText) {
        remove("#unfeature-" + id);
    });
}

function requestLogin() {
    alert("You must be a registered user to do that!");
}

function animate(name) {
    name = "#" + name;
    $(name).effect({
        effect: 'shake',
        direction: 'up',
        distance: 2,
        times: 1
    });
}

function remove(name) {
    $(name).toggle({
        effect: 'slide',
        direction: 'down'
    });
}
