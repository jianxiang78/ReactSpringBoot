import httpClient from "./http-common";


const isUserLoggedIn=()=> {
    let Author = sessionStorage.getItem('Authorization')
    if (Author === null || Author.length<2) return false
    return true
}

const getAuth = () => {
    let Author = sessionStorage.getItem('Authorization')
    if (Author === null) return ''
    return Author
}

const setAuthorization = (Auth) => {
    sessionStorage.setItem('Authorization',Auth)
}


const getLoggedUserName =()=> {
    let user = sessionStorage.getItem('userName')
    if (user === null) return ''
    return user
}

const setLoggedUserName =(userName)=> {
    sessionStorage.setItem('userName',userName)
}

const getLoggedUserRole =()=> {
    let Role = sessionStorage.getItem('userRole')
    if (Role === null) return ''
    return Role
}

const setLoggedUserRole =(userRole)=> {
    sessionStorage.setItem('userRole',userRole)
}

const logout =()=> {
    sessionStorage.removeItem('userRole');
    sessionStorage.removeItem('userName');
    sessionStorage.removeItem('Authorization');
}

const exe = (url,data) => {
    return httpClient.post(url,data)
}

const checkLogin = (data) => {
    if(data.code===500){
        return (
            window.location.href='#/login'
        )
    }
}

const exportedObject = {
    exe,
    isUserLoggedIn,
    getLoggedUserName,
    setLoggedUserName,
    getLoggedUserRole,
    setLoggedUserRole,
    setAuthorization,
    getAuth,
    logout,
    checkLogin
};

export default exportedObject;