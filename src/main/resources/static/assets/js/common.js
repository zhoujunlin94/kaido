const domain = "http://localhost:8490"

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
            window.location.href = domain + "/home.html";
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

function loadMenu(setMenuItems, toast) {
    $.ajax({
        url: domain + '/api/sa/resource/getUserRoleResources?resourceType=MENU',
        type: 'get',
        headers: {
            "Authorization": getToken()
        }
    }).then(res => {
        if (res.code === 0) {
            setMenuItems(res.data)
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

function save(formData, updateUrl, insertUrl, updateSuccessCallback, insertSuccessCallback, toast) {
    let data = JSON.stringify(formData);
    if (formData.id) {
        $.ajax({
            url: domain + updateUrl,
            type: 'post',
            contentType: 'application/json',
            headers: {
                "Authorization": getToken()
            },
            data: data
        }).then(res => {
            if (res.code === 0) {
                updateSuccessCallback()
                toast({
                    showClose: true,
                    message: "更新成功",
                    type: 'success'
                })
            } else {
                toast({
                    showClose: true,
                    message: "更新失败",
                    type: 'error'
                })
            }
        })
    } else {
        $.ajax({
            url: domain + insertUrl,
            type: 'post',
            contentType: 'application/json',
            headers: {
                "Authorization": getToken()
            },
            data: data
        }).then(res => {
            if (res.code === 0) {
                insertSuccessCallback()
                toast({
                    showClose: true,
                    message: "新增成功",
                    type: 'success'
                })
            } else {
                toast({
                    showClose: true,
                    message: "新增失败",
                    type: 'error'
                })
            }
        })
    }
}

function del(deleteUrl, delSuccessCallback, toast) {
    $.ajax({
        url: domain + deleteUrl,
        type: 'post',
        contentType: 'application/json',
        headers: {
            "Authorization": getToken()
        }
    }).then(res => {
        if (res.code === 0) {
            delSuccessCallback()
            toast({
                showClose: true,
                message: "删除成功",
                type: 'success'
            })
        } else {
            toast({
                showClose: true,
                message: "删除失败",
                type: 'error'
            })
        }
    })
}
