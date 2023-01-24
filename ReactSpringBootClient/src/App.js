import React, { Component, Suspense } from 'react'
import {HashRouter, Navigate, Route, Routes} from 'react-router-dom'
import './scss/style.scss'
import dataService from "./tool/dataService";

const loading = (
  <div className="pt-3 text-center">
    <div className="sk-spinner sk-spinner-pulse"></div>
  </div>
)

// Containers
const DefaultLayout = React.lazy(() => import('./layout/DefaultLayout'))

// Pages
const Login = React.lazy(() => import('./views/pages/login/Login'))
const Page404 = React.lazy(() => import('./views/pages/page404/Page404'))
const Page500 = React.lazy(() => import('./views/pages/page500/Page500'))

class App extends Component {
  render() {
      let isUserIn=dataService.isUserLoggedIn()
      let targetPage
      if(isUserIn){
          targetPage=(<Route path="*" name="Home" element={<DefaultLayout />} />)
      }else {
          targetPage=(<Route path="*" element={<Navigate to="login" replace />} />)
      }
    return (
      <HashRouter>
        <Suspense fallback={loading}>
          <Routes>
            <Route exact path="/login" name="Login Page" element={<Login />} />
            <Route exact path="/404" name="Page 404" element={<Page404 />} />
            <Route exact path="/500" name="Page 500" element={<Page500 />} />
            {targetPage}
          </Routes>
        </Suspense>
      </HashRouter>
    )
  }
}

export default App
