const domain = "http://localhost:8490"

function jump2Login(code) {
    if (code === 11012) {
        // token无效
        window.location.href = domain + "/login.html";
    }
}

function setToken(token) {
    localStorage.setItem("Authorization", token)
}

function getToken() {
    return "Bearer " + localStorage.getItem("Authorization")
}

function toastMsg(toast, msg) {
    toast({
        showClose: true,
        message: msg,
        type: 'success'
    })
}

function post(url, requestBody, callback, toast) {
    $.ajax({
        url: domain + url,
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify(requestBody),
        headers: {
            "Authorization": getToken()
        }
    }).then(res => {
        if (res.code === 0) {
            callback(res.data)
        } else {
            toast({
                showClose: true,
                message: res.msg,
                type: 'error'
            })
            jump2Login(res.code)
        }
    })
}

function get(url, callback, toast) {
    $.ajax({
        url: domain + url,
        type: 'get',
        headers: {
            "Authorization": getToken()
        }
    }).then(res => {
        if (res.code === 0) {
            callback(res.data)
        } else {
            toast({
                showClose: true,
                message: res.msg,
                type: 'error'
            })
            jump2Login(res.code)
        }
    })
}

function save(requestBody, updateUrl, insertUrl, updateSuccessCallback, insertSuccessCallback, toast) {
    let url;
    if (requestBody.id) {
        url = domain + updateUrl;
        post(url, requestBody, updateSuccessCallback, toast);
    } else {
        url = domain + insertUrl;
        post(url, requestBody, insertSuccessCallback, toast);
    }
}
