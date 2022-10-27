import { useEffect, useState } from "react";
import { useFormik } from "formik";
import { DataTable, DataTablePageParams } from 'primereact/datatable'
import { Column } from 'primereact/column'
import { Button } from 'primereact/button'
import { confirmDialog } from 'primereact/confirmdialog'
import Router from "next/router";

import { Layout } from "components/layout";
import { Input, InputCPF } from "components/common";
import { Usuario } from "app/models/usuarios";
import { Page } from "app/models/common/page";
import { useUsuarioService } from "app/services";

interface ConsultaUsuariosForm {
  rg?: string;
  cpf?: string;
  nome?: string;
}

export const ListagemUsuarios: React.FC = () => {
  const service = useUsuarioService();
  const [loading, setLoading] = useState<boolean>(false);

  useEffect(() => handlePage(null), []);

  const [usuarios, setUsuarios] = useState<Page<Usuario>>({
    content: [],
    first: 0,
    number: 0,
    size: 5,
    totalElements: 0
  });

  const handleSubmit = (filtro: ConsultaUsuariosForm) => {
    handlePage(null);
  }

  const handlePage = (event: DataTablePageParams | null) => {
    setLoading(true);
    service.find(filtro?.nome, filtro?.cpf, filtro?.rg, event?.page, event?.rows)
      .then(result => setUsuarios({...result, first: event?.first || 1}))
      .finally(() => setLoading(false));
  }

  const { handleSubmit: formikSubmit, values: filtro, handleChange} = useFormik<ConsultaUsuariosForm>({
    onSubmit: handleSubmit,
    initialValues: { rg: '', cpf: '', nome: '' },
  });

  const deletar = (usuario: Usuario) => {
    if(usuario.id){
      service.deletar(usuario.id)
        .then(result => {
          handlePage(null);
        });
    }
  };

  const actionTemplate = (registro: Usuario) => {
    return (
      <div>
        <Button
          label="Editar"
          className="p-button-rounded p-button-info"
          onClick={ () => Router.push("/cadastros/usuarios?id="+registro.id)}
        />
        &nbsp;
        <Button
          label="Deletar"
          className="p-button-rounded p-button-danger"
          onClick={event => {
            confirmDialog({
              message: "Realmente deseja excluir esse registro?",
              acceptLabel: "Sim",
              rejectLabel: "Não",
              header: "Confirmação necessária",
              accept: () => deletar(registro)
            })
          }}
        />
      </div>
    )
  }

  return (
    <Layout titulo="Usuários">
      <form onSubmit={formikSubmit}>
        <div className="columns">
          <Input
            id="nome"
            name="nome"
            label="Nome"
            value={filtro.nome}
            autoComplete="off"
            onChange={handleChange}
            columnClasses="is-full"
          />
        </div>
        <div className="columns">
          <InputCPF
            id="cpf"
            name="cpf"
            label="CPF"
            value={filtro.cpf}
            autoComplete="off"
            onChange={handleChange}
            columnClasses="is-half"
          />
          <Input
            id="rg"
            name="rg"
            label="RG"
            value={filtro.rg}
            autoComplete="off"
            onChange={handleChange}
            columnClasses="is-half"
          />
        </div>
        <div className="field is-grouped">
          <div className="control is-link">
            <button type="submit" className="button is-primary">
              Consultar
            </button>
          </div>
          <div className="control is-link">
            <button type="submit" onClick={() => Router.push("/cadastros/usuarios")} className="button is-success">
              Novo
            </button>
          </div>
        </div>
      </form>

      <br />

      <div className="columns">
        <div className="is-full">
          
          <DataTable 
            lazy paginator
            loading={loading}
            onPage={handlePage}
            rows={usuarios.size}
            first={usuarios.first}
            value={usuarios.content}
            totalRecords={usuarios.totalElements}
            emptyMessage="Nenhum registro encontrado"
          > 
            <Column field="id" header="Código" />
            <Column field="nome" header="Nome" />
            <Column field="cpf" header="CPF" />
            <Column field="rg" header="RG" />
            <Column body={actionTemplate} />
          </DataTable>
        </div>
      </div>
    </Layout>
  )
}