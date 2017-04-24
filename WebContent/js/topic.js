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

function agree(id) {

}

function addComment(id) {
    $.post("feature", {
        id: id,
        do: "addComment"
    }).done(function(responseText) {

    });
}