function createCookie(name, value, days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + value + expires + "; path=/";
}

function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
}

function eraseCookie(name) {
    createCookie(name, "", -1);
}

function setAPI(key) {
    createCookie("current_fin_api_key", key, 7);
    createCookie("current_fin_api_url", apis[key].url, 7);
}

function getAPIurl() {
    return readCookie("current_fin_api_url");
}

function getAPIkey() {
    return readCookie("current_fin_api_key");
}

var rebuildUI = function () {

    // Build a system
    const ui = SwaggerUIBundle({
        url: getAPIurl(),
        dom_id: '#swagger-ui',
        deepLinking: true,
        presets: [
            SwaggerUIBundle.presets.apis,
            SwaggerUIStandalonePreset
        ],
        plugins: [
            SwaggerUIBundle.plugins.DownloadUrl
        ],
        layout: "StandaloneLayout"
    });

    var select = d3.select(".topbar .download-url-wrapper")
            .append('select')
            .attr('class', 'select')
            .on('change', onchange);

    select.selectAll('option')
            .data(Object.keys(apis)).enter()
            .append('option')
            .property("selected", function (d) {
                return d === getAPIkey();
            })
            .attr("value", function (d) {
                return d;
            })
            .text(function (d) {
                return apis[d].name;
            });

    function onchange() {
        var selectValue = d3.select('select').property('value');
        setAPI(selectValue);
        rebuildUI();
    }

    window.ui = ui;

};

var initApiSwitcher = function(){
  if(getAPIkey()==undefined){
    setAPI(defaultApi);  
  }
  rebuildUI();
};
