import React from 'react';
import Home from './Main/Home';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import './App.scss';
import WorkBook from './WorkBook/WorkBook';

function App() {
  const router = createBrowserRouter([
    {
      path: '/',
      element: <Home></Home>,
    },
    {
      path: '/workBook',
      element: <WorkBook></WorkBook>,
    },
  ]);

  return <RouterProvider router={router}></RouterProvider>;
}
export default App;
