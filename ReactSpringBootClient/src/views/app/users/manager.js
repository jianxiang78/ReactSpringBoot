import React, {useCallback, useEffect, useState} from 'react'

import {
  CAlert,
  CButton,
  CCard,
  CCardBody,
  CCardHeader,
  CCol,
  CForm,
  CFormInput,
  CInputGroup,
  CInputGroupText,
  CModal,
  CModalBody,
  CModalFooter,
  CModalHeader,
  CModalTitle,
  CPagination, CPaginationItem,
  CRow,
  CTable,
  CTableBody,
  CTableDataCell,
  CTableFoot,
  CTableHead,
  CTableHeaderCell,
  CTableRow,
} from '@coreui/react'

import dataService from '../../../tool/dataService';


const UserManager = () => {

  let [visibleAlertSuccess, setVisibleAlertSuccess] = useState(false)
  let [failMsg, setFailMsg] = useState('')
  let [visibleAlertFail, setVisibleAlertFail] = useState(false)
  let [addVisible, setAddVisible] = useState(false)
  let [editVisible, setEditVisible] = useState(false)
  let [delVisible, setDelVisible] = useState(false)
  let [addSubmitDisabled, setAddSubmitDisabled] = useState(true)

  let [userNo, setUserNo ]= useState()
  let [userLoginName, setUserLoginName ]= useState('')
  let [userPasswd, setUserPasswd ]= useState('')
  let [invalidConfirmPassword, setInvalidConfirmPassword ]= useState(true)
  let [userTrueName, setUserTrueName ]= useState('')
  let [userRole, setUserRole ]= useState('')

  let [employees, setEmployees] = useState([]);
  let [pageDataTotal, setPageDataTotal]= useState(0)
  let [pageTotal, setPageTotal]= useState(1)
  let [pageNum, setPageNum]= useState(1)
  let [pageSize, setPageSize]= useState(10)

  let [disabledPagePrevious, setDisabledPagePrevious]= useState(false)
  let [disabledPageNext, setDisabledPageNext]= useState(false)

  const ItemSet=(item)=>{
    setUserNo(item.userNo)
    setUserLoginName(item.userLoginName)
    setUserTrueName(item.userTrueName)
  }

  const checkPassword=(Password2)=>{
    if(userPasswd===Password2){
      setInvalidConfirmPassword(false)
      setAddSubmitDisabled(false)
    }else {
      setInvalidConfirmPassword(true)
      setAddSubmitDisabled(true)
    }
  }

  function initData () {
    setUserLoginName('')
    setUserTrueName('')
    setUserPasswd('')
    setInvalidConfirmPassword(true)

  }

  const ContentAdd = () => {

    const handleSubmit = (event) => {
      event.preventDefault()
      setAddVisible(false)
      const postData ={
        userLoginName:userLoginName,
        userPasswd:userPasswd,
        userTrueName:userTrueName,
        userRole:userRole
      }
      console.log('Printing postData', postData);
      dataService.exe("appUser/add",postData)
          .then(response => {
            console.log('add successfully', response.data);
            DataAlertShow(response.data)
            initTable();
          })
          .catch(error => {
            console.log('Something wrong', error);
          })
    }

    return (
        <>
          <CButton onClick={() => initData()>setAddVisible(true)}>Add New User</CButton>
          <CModal scrollable visible={addVisible} onClose={() => setAddVisible(false)} alignment="center">
            <CModalHeader>
              <CModalTitle>Add New User</CModalTitle>
            </CModalHeader>
            <CForm onSubmit={handleSubmit} >
              <CModalBody>
                <CInputGroup className="mb-3">
                  <CInputGroupText className="col-sm-5">Login Name</CInputGroupText>
                  <CFormInput required onChange={event => setUserLoginName(event.target.value)}/>
                </CInputGroup>
                <CInputGroup className="mb-3">
                  <CInputGroupText className="col-sm-5">Password</CInputGroupText>
                  <CFormInput type="password" required onChange={event => setUserPasswd(event.target.value)}/>
                </CInputGroup>
                <CInputGroup className="mb-3">
                  <CInputGroupText className="col-sm-5">Confirm Password</CInputGroupText>
                  <CFormInput
                      type="password"
                      id="addUserConfirmPassword"
                      feedback="Password didn't matched."
                      invalid={invalidConfirmPassword}
                      required
                      onChange={event => checkPassword(event.target.value)}
                  />
                </CInputGroup>
                <CInputGroup className="mb-3">
                  <CInputGroupText className="col-sm-5">True Name</CInputGroupText>
                  <CFormInput required onChange={event => setUserTrueName(event.target.value)}/>
                </CInputGroup>
                <CInputGroup className="mb-3">
                  <CInputGroupText className="col-sm-5">User Role</CInputGroupText>
                  <CFormInput required onChange={event => setUserRole(event.target.value)}/>
                </CInputGroup>
              </CModalBody>
              <CModalFooter>
                <CButton color="secondary" onClick={() => setAddVisible(false)}>
                  Close
                </CButton>
                <CButton color="primary" type="submit" disabled={addSubmitDisabled}>
                  Save
                </CButton>
              </CModalFooter>
            </CForm>
          </CModal>
        </>
    )
  }
  const ContentEdit = () => {

    const handleEdit = (event) => {
      event.preventDefault()
      setEditVisible(false)
      const postData ={
        userNo:userNo,
        userLoginName:userLoginName,
        userTrueName:userTrueName
      }
      dataService.exe("appUser/edit",postData)
          .then(response => {
            console.log('Edit successfully', response.data);
            DataAlertShow(response.data)
            initTable();
          })
          .catch(error => {
            console.log('Edit wrong', error);
          })
    }

    return (
        <>
          <CModal scrollable visible={editVisible} onClose={() => setEditVisible(false)} alignment="center">
            <CModalHeader>
              <CModalTitle>Edit User</CModalTitle>
            </CModalHeader>
            <CForm onSubmit={handleEdit}>
              <CModalBody>
                <CInputGroup className="mb-3">
                  <CInputGroupText className="col-sm-4">User No</CInputGroupText>
                  <CFormInput readOnly value={userNo} disabled />
                </CInputGroup>
                <CInputGroup className="mb-3">
                  <CInputGroupText className="col-sm-4">User Login Name</CInputGroupText>
                  <CFormInput required value={userLoginName} onChange={event => setUserLoginName(event.target.value)}/>
                </CInputGroup>
                <CInputGroup className="mb-3">
                  <CInputGroupText className="col-sm-4">User True Name</CInputGroupText>
                  <CFormInput required value={userTrueName} onChange={event => setUserTrueName(event.target.value)}/>
                </CInputGroup>
              </CModalBody>
              <CModalFooter>
                <CButton color="secondary" onClick={() => setEditVisible(false)}>
                  Close
                </CButton>
                <CButton color="primary" type="submit">
                  Save
                </CButton>
              </CModalFooter>
            </CForm>
          </CModal>
        </>
    )
  }
  const ContentDel = () => {

    const handleDel = (event) => {
      event.preventDefault()
      setDelVisible(false)
      const postData ={
        userNo:userNo
      }
      dataService.exe("appUser/del",postData)
          .then(response => {
            console.log('Del successfully', response.data);
            DataAlertShow(response.data)
            initTable();
          })
          .catch(error => {
            console.log('Del wrong', error);
          })
    }

    return (
        <>
          <CModal visible={delVisible} onClose={() => setDelVisible(false)} alignment="center">
            <CModalHeader>
              <CModalTitle>Delete User</CModalTitle>
            </CModalHeader>
            <CForm onSubmit={handleDel}>
              <CModalBody>
                <CInputGroup className="mb-3">
                  <CInputGroupText className="col-sm-4">User No</CInputGroupText>
                  <CFormInput value={userNo} readOnly disabled />
                </CInputGroup>
                <CInputGroup className="mb-3">
                  <CInputGroupText className="col-sm-4">User Login Name</CInputGroupText>
                  <CFormInput required value={userLoginName} readOnly disabled />
                </CInputGroup>
                <CInputGroup className="mb-3">
                  <CInputGroupText className="col-sm-4">User True Name</CInputGroupText>
                  <CFormInput required value={userTrueName} readOnly disabled />
                </CInputGroup>
              </CModalBody>
              <CModalFooter>
                <CButton color="secondary" onClick={() => setDelVisible(false)}>
                  Close
                </CButton>
                <CButton color="primary" type="submit">
                  Delete
                </CButton>
              </CModalFooter>
            </CForm>
          </CModal>
        </>
    )
  }

  const initTable= useCallback(() => {

    const PagePrevious = () =>{
      if(pageNum>1) {
        setDisabledPagePrevious(false)
      }else {
        setDisabledPagePrevious(true)
      }
    }

    const PageNext = (pTotal) =>{
      if(pageNum<pTotal){
        setDisabledPageNext(false)
      }else {
        setDisabledPageNext(true)
      }
    }

    function dataPage(resultData) {
      if(resultData.code===1){
        setEmployees(resultData.rows);
        setPageDataTotal(resultData.total)
        let PageTotal=Math.ceil(resultData.total/pageSize)
        setPageTotal(PageTotal)
        PagePrevious()
        PageNext(PageTotal)
      }
    }

    setPageSize(10);
    let initData={
      pageSize: pageSize,
      pageNum: pageNum,
      orderByColumn: 'user_no',
      isAsc: 'desc'
    }
    dataService.exe("appUser/list",initData)
        .then(response => {
          console.log('Printing employees data', response.data);
          dataPage(response.data)
        })
        .catch(error => {
          console.log('Something went wrong', error);
        })
  }, [pageSize,pageNum])

  useEffect(() => {
    initTable();
  }, [initTable,pageNum]);

  function DataAlertShow(resultData) {
    if(resultData.code===0){
      setVisibleAlertFail(true)
      setFailMsg(resultData.msg)
      setVisibleAlertSuccess(false)
    }
    if(resultData.code===1){
      setVisibleAlertSuccess(true)
      setVisibleAlertFail(false)
    }
  }

  return (
      <>
        <CRow>
          <CCol xs>
            <CCard className="mb-4">
              <CCardHeader>App Users</CCardHeader>
              <CCardBody>
                <CTable align="middle" className="mb-0 border" hover responsive>
                  <CTableHead>
                    <CTableRow>
                      <CTableHeaderCell colSpan="5" className="text-right">
                        <CAlert color="success" dismissible visible={visibleAlertSuccess} onClose={() => setVisibleAlertSuccess(false)} className="text-center">Success!</CAlert>
                        <CAlert color="danger" dismissible visible={visibleAlertFail} onClose={() => setVisibleAlertFail(false)} className="text-center">Failed! <span>{failMsg}</span></CAlert>
                        <div>{ContentAdd()}</div>
                      </CTableHeaderCell>
                    </CTableRow>
                  </CTableHead>
                  <CTableHead color="light">
                    <CTableRow>
                      <CTableHeaderCell>User No</CTableHeaderCell>
                      <CTableHeaderCell>Login Name</CTableHeaderCell>
                      <CTableHeaderCell>True Name</CTableHeaderCell>
                      <CTableHeaderCell>Role</CTableHeaderCell>
                      <CTableHeaderCell>Action</CTableHeaderCell>
                    </CTableRow>
                  </CTableHead>
                  <CTableBody>
                    {employees.map((item, index) => (
                        <CTableRow v-for="item in tableItems" key={index}>
                          <CTableDataCell>
                            <div className="float-start">
                              <strong>{item.userNo}</strong>
                            </div>
                          </CTableDataCell>
                          <CTableDataCell>
                            <div className="clearfix">
                              <div className="float-start">
                                <strong>{item.userLoginName}</strong>
                              </div>
                            </div>
                          </CTableDataCell>
                          <CTableDataCell>
                            <div className="clearfix">
                              <div className="float-start">
                                <strong>{item.userTrueName}</strong>
                              </div>
                            </div>
                          </CTableDataCell>
                          <CTableDataCell>
                            <div className="clearfix">
                              <div className="float-start">
                                <strong>{item.userRole}</strong>
                              </div>
                            </div>
                          </CTableDataCell>
                          <CTableDataCell>
                            <CButton color="warning" onClick={() => ItemSet(item)>setEditVisible(true)}>Edit</CButton>
                            &nbsp;&nbsp;
                            <CButton color="danger" onClick={() =>ItemSet(item)>setDelVisible(true)}>Delete</CButton>
                          </CTableDataCell>
                        </CTableRow>
                    ))
                    }
                  </CTableBody>
                  <CTableFoot>
                    <CTableRow>
                      <CTableHeaderCell colSpan="5">
                        <CPagination align="center" aria-label="Page navigation">
                          <CPaginationItem disabled={disabledPagePrevious} onClick={()=>setPageNum(pageNum-1)}>Previous</CPaginationItem>
                          <CPaginationItem>{pageNum}/{pageTotal}</CPaginationItem>
                          <CPaginationItem>Total:{pageDataTotal},PageSize:{pageSize}</CPaginationItem>
                          <CPaginationItem disabled={disabledPageNext} onClick={()=>setPageNum(pageNum+1)}>Next</CPaginationItem>
                        </CPagination>
                      </CTableHeaderCell>
                    </CTableRow>
                  </CTableFoot>
                </CTable>
              </CCardBody>
            </CCard>
          </CCol>
        </CRow>
        {ContentDel()}
        {ContentEdit()}
      </>
  )
}

export default UserManager
