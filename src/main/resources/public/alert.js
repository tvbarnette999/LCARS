let socket;
const GREEN = 1;
const YELLOW = 2;
const RED = 3;
const classes = ["grey", "green", "yellow", "red"];
let status = RED;
function connectSocket() {
    let url = (window.location.protocol === 'https:' ? "wss" : "ws") + "://" + window.location.host + "/stream";
    socket = new persistentwebsocket.PersistentWebsocket(url, {
        pingIntervalSeconds: 60
    });
    socket.onmessage = function (e) {
        if (e.data === 'pong') {
            return;
        }
        // do stuff... only unicast messages would come from this
        let data = e.data; //JSON.parse(e.data);
        let mem = parseFloat(data);
       // console.log(mem);
        let alert =  $("#alert");
        let newStatus = status;
        if (mem > .9) {
            newStatus = RED
            $("#redKlaxon")[0].play();
        } else if (mem > .75) {
            newStatus = YELLOW;
            $("#yellowKlaxon")[0].play();
        } else {
            newStatus = GREEN;
        }
        if (newStatus !== status) {
            status = newStatus;
            alert.addClass(classes[status]);
            for (let i = 0; i < classes.length; i++) {
                if (i !== newStatus) {
                    alert.removeClass(classes[i]);
                }
            }
        }
    }
    socket.onopen = (e) => console.log(e);
    socket.onclose = (e) => console.log(e);
    socket.onerror = (e) => console.log(e);

    socket.open();
}
$(document).ready(() => {
    connectSocket();
});