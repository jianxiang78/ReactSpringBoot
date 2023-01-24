import axIos from "axios";

const instance=axIos.create({
    baseURL: 'http://localhost:8080',
    headers: {
        "Content-Type": "application/x-www-form-urlencoded; charset=utf-8",
        "Access-Control-Allow-Origin": "*"
    }
});

function getAuthorization () {
    let Author = sessionStorage.getItem('Authorization')
    if (Author === null) return ''
    return Author
}

instance.interceptors.request.use(
    (config) => {
        config.headers.authorization = getAuthorization()
        return config
    }
)

export default instance