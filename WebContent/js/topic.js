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
    var name = "topic";
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

function disagree(id) {
    var name = "topic";
    if (!document.getElementById(name).classList.contains('green')) {
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

function addComment(id, agree, parent) {
    $.post("feature", {
        id: id,
        subj: document.getElementById("commentSubject"),
        arg: document.getElementById("commentArgument"),
        agree: agree,
        do: "addComment"
    }).done(function(responseText) {
        if(agree == true) {
            if(parent == null) {
                var $newDiv = document.getElementById("agreeComment");
                var subject = $("<h5 class = 'bold'>" + document.getElementById("commentSubject") + "</h5>");
                var argument = $("<p>" + document.getElementById("commentArgument") + "</p>");
                var vote = document.getElementById("voteCount");
                var footer = document.getElementById("commentFooter");
                $newDiv.append(subject, argument, vote);
                document.getElementById("agreeComments").prepend($newDiv);

                document.getElementById("agreeComments").prepend(newDiv);
            }
        }
    });
}
