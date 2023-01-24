import React, {useState} from 'react'
import {
  CAlert,
  CButton,
  CCard,
  CCardBody,
  CCardGroup,
  CCol,
  CContainer,
  CForm,
  CFormInput,
  CInputGroup,
  CInputGroupText,
  CRow,
} from '@coreui/react'
import CIcon from '@coreui/icons-react'
import { cilLockLocked, cilUser } from '@coreui/icons'
import dataService from "../../../tool/dataService";

const Login = () => {

  let [visibleAlertFail, setVisibleAlertFail] = useState(false)
  let [userLoginName, setUserLoginName ]= useState('')
  let [userPasswd, setUserPasswd ]= useState('')

  const handleLogin = (event) => {
    event.preventDefault()
    const postData ={
      userLoginName:userLoginName,
      userPasswd:userPasswd
    }
    console.log('Printing postData', postData);
    dataService.exe("user/login",postData)
        .then(response => {
          console.log('add successfully', response.data);
          initLogin(response.data)
        })
        .catch(error => {
          console.log('Something wrong', error);
        })
  }

  const initLogin=(data)=>{
    if(data.code===1){
      dataService.setAuthorization(data.data.Authorization)
      dataService.setLoggedUserName(data.data.userTrueName)
      dataService.setLoggedUserRole(data.data.userRole)
      window.location.href='/'
    }else {
      setVisibleAlertFail(true)
    }
  }

  const clearLogin=()=>{
    dataService.logout()
  }

  return (
    <div className="bg-light min-vh-100 d-flex flex-row align-items-center">
      <CContainer>
        <CRow className="justify-content-center">
          <CCol md={8}>
            <CCardGroup>
              <CCard className="p-4">
                <CCardBody>
                  <CForm onSubmit={handleLogin} >
                    <h1>Web App Login</h1>
                    <p className="text-medium-emphasis">Sign In to your account</p>
                    <CAlert color="danger" dismissible visible={visibleAlertFail} onClose={() => setVisibleAlertFail(false)} className="text-center">Failed! Login name or password error</CAlert>
                    <CInputGroup className="mb-3">
                      <CInputGroupText>
                        <CIcon icon={cilUser} />
                      </CInputGroupText>
                      <CFormInput required placeholder="Username" autoComplete="username" onChange={event => setUserLoginName(event.target.value)}/>
                    </CInputGroup>
                    <CInputGroup className="mb-4">
                      <CInputGroupText>
                        <CIcon icon={cilLockLocked} />
                      </CInputGroupText>
                      <CFormInput required
                        type="password"
                        placeholder="Password"
                        autoComplete="current-password" onChange={event => setUserPasswd(event.target.value)}
                      />
                    </CInputGroup>
                    <CRow>
                      <CCol xs={6} className="text-right">
                        <CButton color="primary" className="px-4 text-right" type="submit">
                          Login
                        </CButton>
                      </CCol>
                    </CRow>
                  </CForm>
                </CCardBody>
              </CCard>
              <CCard className="text-white bg-primary py-5">
                <CCardBody className="text-center">
                  <div>
                    <h2>React & SpringBoot</h2>
                    <h2>A Simple Example</h2>
                  </div>
                </CCardBody>
              </CCard>
            </CCardGroup>
          </CCol>
        </CRow>
      </CContainer>
      {clearLogin()}
    </div>
  )
}

export default Login
