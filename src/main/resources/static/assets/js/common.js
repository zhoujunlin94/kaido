const domain = "http://localhost:8090"

function setToken(token) {
    localStorage.setItem("Authorization", token)
}

function login(loginParam, toast) {
    $.ajax({
        url: domain + '/api/sa/user/login',
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify(loginParam)
    }).then(res => {
        if (res.code === 0) {
            setToken(res.data.tokenValue)
            window.location.href = "./home.html";
        } else {
            toast({
                showClose: true,
                message: res.msg,
                type: 'error'
            })
        }
    })
}

function getToken() {
    return "Bearer " + localStorage.getItem("Authorization")
}

function loadMenu(menuItems, toast) {
    $.ajax({
        url: domain + '/api/sa/resource/getUserRoleResources?resourceType=MENU',
        type: 'get',
        headers: {
            "Authorization": getToken()
        }
    }).then(res => {
        if (res.code === 0) {
            for (let i = 0; i < res.data.length; i++) {
                menuItems.push(res.data[i]);
            }
        } else {
            toast({
                showClose: true,
                message: res.msg,
                type: 'error'
            })
        }
    })
}

function page(url, queryParam, setPageInfo, toast) {
    $.ajax({
        url: domain + url,
        type: 'post',
        contentType: 'application/json',
        headers: {
            "Authorization": getToken()
        },
        data: JSON.stringify(queryParam)
    }).then(res => {
        if (res.code === 0) {
            setPageInfo(res.data)
        } else {
            toast({
                showClose: true,
                message: res.msg,
                type: 'error'
            })
        }
    })

}