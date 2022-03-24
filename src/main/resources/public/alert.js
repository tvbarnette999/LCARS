let socket;
const GREY = 0;
const GREEN = 1;
const YELLOW = 2;
const RED = 3;
const classes = ["grey", "green", "yellow", "red"];
let status = GREY;
function setStatus(newStatus) {
    let alert =  $("#alert");
    if (newStatus !== status) {
        status = newStatus;
        if (status === RED) {
            $("#redKlaxon")[0].play();
        } else if (status === YELLOW) {
            $("#yellowKlaxon")[0].play();
        }
        alert.addClass(classes[status]);
        for (let i = 0; i < classes.length; i++) {
            if (i !== newStatus) {
                alert.removeClass(classes[i]);
            }
        }
    }
}
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
        let mem = parseFloat(data)
        let newStatus = GREEN;
        // hysteresis - must drop to 80% after triggering
        if ((status === RED && mem > .8) || mem > .85) {
            newStatus = RED
        } else if ((status === YELLOW && mem > .7) || mem > .75) { // must drop to 70 after triggering
            newStatus = YELLOW;
        }
        setStatus(newStatus);
    }
    socket.onopen = (e) => console.log(e);
    socket.onclose = (e) => setStatus(GREY);
    socket.onerror = (e) => console.log(e);

    socket.open();
}
$(document).ready(() => {
    let prom = $("#loadTone")[0].play();
    if (prom) {
        prom.then(() => {
            $("#lcars").removeClass("silent");
            $("#readyMessage")[0].play();
        }).catch(err => {
            console.log("NOPE!");
            $("#top_mid").click(() => {
                $("#readyMessage")[0].play();
                $("#lcars").removeClass("silent");
            });
        });
    }
    connectSocket();
});