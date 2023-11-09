function post(url, postData) {
    let result;
    $.ajax({
        url: url,
        type: 'post',
        contentType: 'application/json',
        data: postData
    }).then(res => {
        result = res;
    })
    return result;
}