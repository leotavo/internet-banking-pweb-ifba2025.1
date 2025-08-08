import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import CadastroUsuario from './pages/CadastroUsuario';
import Login from './pages/Login';
import Conta from './pages/Conta';
import Operacoes from './pages/Operacoes';
import Extrato from './pages/Extrato';

export default function App() {
  return (
    <Router>
      <Header />
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/cadastro" element={<CadastroUsuario />} />
        <Route path="/conta" element={<Conta />} />
        <Route path="/operacoes" element={<Operacoes />} />
        <Route path="/extrato" element={<Extrato />} />
      </Routes>
    </Router>
  );
}
