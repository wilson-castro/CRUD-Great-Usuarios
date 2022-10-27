import * as Yup from 'yup';

const campoObrigatorioMensagem = "Campo obrigatório";
const campoObrigatorioValidation = Yup.string().trim().required(campoObrigatorioMensagem);

export const validationScheme = Yup.object().shape({
  cpf: Yup.string().trim()
    .required(campoObrigatorioMensagem)
    .length(14, "CPF inválido"),
  dataNascimento: Yup.string().trim()
    .required(campoObrigatorioMensagem)
    .length(10, "Data inválida"),
  rg: campoObrigatorioValidation,
  nome: campoObrigatorioValidation,
  nomeMae: campoObrigatorioValidation
});