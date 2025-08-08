import React, { useState } from 'react';
import usuarioService from '../services/usuarioService';

export default function CadastroUsuario() {
  const [form, setForm] = useState({ nome: '', cpf: '', email: '', senha: '' });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await usuarioService.cadastrar(form);
      alert('Usuário cadastrado com sucesso!');
    } catch (err) {
      alert('Erro ao cadastrar usuário');
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input name="nome" placeholder="Nome" onChange={handleChange} />
      <input name="cpf" placeholder="CPF" onChange={handleChange} />
      <input name="email" placeholder="Email" onChange={handleChange} />
      <input name="senha" type="password" placeholder="Senha" onChange={handleChange} />
      <button type="submit">Cadastrar</button>
    </form>
  );
}
