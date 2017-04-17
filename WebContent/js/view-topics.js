function upvote(id) {
    if (!document.getElementById("upvote-" + id).classList.contains('green')) {
        $.post("feature", {
            id: id,
            do: "upvote"
        }).done(function(responseText) {
            if (document.getElementById("downvote-" + id).classList.contains('red')) {
                document.getElementById("count-" + id).innerHTML -= -2;
            } else {
                document.getElementById("count-" + id).innerHTML -= -1;
            }
            document.getElementById("upvote-" + id).classList.add('green');
            document.getElementById("upvote-" + id).classList.add('bold');
            document.getElementById("downvote-" + id).classList.remove('red');
            document.getElementById("downvote-" + id).classList.remove('bold');
        });
    }
}

function downvote(id) {
    if (!document.getElementById("downvote-" + id).classList.contains('red')) {
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
            document.getElementById("downvote-" + id).classList.add('red');
            document.getElementById("downvote-" + id).classList.add('bold');
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
        document.getElementById("feature-" + id).outerHTML = "";
    });
}

function unfeature(id) {
    $.post("feature", {
        id: id,
        do: "unfeature"
    }).done(function(responseText) {
        document.getElementById("unfeature-" + id).outerHTML = "";
    });
}

function requestLogin() {
	alert("You must be a registered user to do that!");
}
