import React from 'react';
import { Link } from 'react-router-dom';

export default function Header() {
  return (
    <nav>
      <ul>
        <li><Link to="/">Login</Link></li>
        <li><Link to="/cadastro">Cadastro</Link></li>
        <li><Link to="/conta">Conta</Link></li>
        <li><Link to="/operacoes">Operações</Link></li>
        <li><Link to="/extrato">Extrato</Link></li>
      </ul>
    </nav>
  );
}
