import React from 'react'

const Dashboard = React.lazy(() => import('./views/dashboard/Dashboard'))

//web app
const Manager = React.lazy(() => import('./views/app/users/manager'))

const routes = [
  { path: '/', exact: true, name: 'Home' },
  { path: '/dashboard', name: 'Dashboard', element: Dashboard },

  { path: '/users/manager', name: 'Users', element: Manager },
]

export default routes
