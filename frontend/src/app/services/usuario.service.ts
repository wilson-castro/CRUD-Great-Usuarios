import { AxiosResponse } from "axios";

import { httpClient } from "app/http";
import { Page } from "app/models/common/page";
import { Usuario } from "app/models/usuarios";

const resourceURL: string = "/api/usuarios";

export const useUsuarioService = () => {

  const salvar = async (usuario: Usuario): Promise<Usuario> => {
    const response: AxiosResponse<Usuario> = await httpClient.post<Usuario>(resourceURL, usuario);
    return response.data;
  };

  const atualizar = async (usuario: Usuario): Promise<void> => {
    const url: string = `${resourceURL}/${usuario.id}`;
    await httpClient.put<Usuario>(url, usuario);
  };

  const carregarUsuario = async (id: any): Promise<Usuario> => {
    const url: string = `${resourceURL}/${id}`;
    const response: AxiosResponse<Usuario> = await httpClient.get(url);
    return response.data;
  }

  const deletar = async (id: string): Promise<void> => {
    const url: string = `${resourceURL}/${id}`;
    await httpClient.delete(url);
  };

  const find = async (
    nome: string = '',
    cpf: string = '',
    rg: string = '',
    page: number = 0,
    size: number = 5): Promise<Page<Usuario>> => {
    
    const url = `${resourceURL}?nome=${nome}&cpf=${cpf}&rg=${rg}&page=${page}&size=${size}`;
    const response: AxiosResponse<Page<Usuario>> = await httpClient.get(url);

    return response.data;
  }

  return {
    find,
    salvar,
    deletar,
    atualizar,
    carregarUsuario
  };
}