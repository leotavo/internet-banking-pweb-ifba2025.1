import api from './api';

const cadastrar = (dados) => {
  return api.post('/usuarios', dados);
};

export default { cadastrar };
