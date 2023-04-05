import React from 'react';
import Home from './main/Home';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import './App.scss';
import WorkBook from './WorkBook/WorkBook';
import Result from './Result/Result';

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
    {
      path: '/result',
      element: <Result></Result>,
    },
  ]);

  return <RouterProvider router={router}></RouterProvider>;
}
export default App;
