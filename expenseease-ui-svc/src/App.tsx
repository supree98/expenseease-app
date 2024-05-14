import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import SignUp from './components/auth/SignUp'
import Home from './components/Home';
import Groups from './components/group/Groups';
import Login from './components/auth/Login';
import Expenses from './components/expense/Expenses';

function App() {
  return (<Router>
    <Routes>
      <Route path="/" element={<Home />}>
        <Route path="/register" element={<SignUp />} />
        <Route path="/login" element={<Login />} />
        <Route path="/groups" element={<Groups />} />
        <Route path="/expenses" element={<Expenses />} />
      </Route>
    </Routes>
  </Router>)
}

export default App;