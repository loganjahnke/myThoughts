// function doDelete(id) {
//     $.post("feature", {
//         id: id,
//         do: "delete"
//     }).done(function(responseText) {
//         location.reload();
//     });
// }

function toggle(id) {
    $.post("assign-moderator", {
        userid: id
    }).done(function(responseText) {
        if (responseText == "moderator") {
            document.getElementById("toggle-" + id).innerHTML = "<a onclick='toggle(" + id + ")' class='mt-button-tiny'><i class='fa fa-times'></i> Remove Moderator</a></li>";
        } else {
            document.getElementById("toggle-" + id).innerHTML = "<a onclick='toggle(" + id + ")' class='mt-button-tiny'><i class='fa fa-male'></i> Assign Moderator</a></li>";
        }
    });
}