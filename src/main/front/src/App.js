import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './component/Home';
import Room from './component/Room';
function App() {
  return (
    <div className="App">
      <BrowserRouter>
          <Routes>
            <Route path="/" exact element={<Home />} />
            <Route path="/room" exact element={<Room/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
