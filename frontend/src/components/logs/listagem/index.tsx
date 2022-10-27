import { useEffect, useState } from "react";
import { useFormik } from "formik";
import { DataTable, DataTablePageParams } from 'primereact/datatable'
import { Column } from 'primereact/column'

import { Layout } from "components/layout";
import { Input } from "components/common";
import { Log } from "app/models/logs";
import { Page } from "app/models/common/page";
import { useLogService } from "app/services/log.service";

interface ConsultaLogsForm {
  tipo?: string;
}

export const ListagemLogs: React.FC = () => {
  const service = useLogService();
  const [loading, setLoading] = useState<boolean>(false);

  useEffect(() => handlePage(null), []);

  const [ logs, setLogs] = useState<Page<Log>>({
  content: [],
  first: 0,
  number: 0,
  size: 5,
  totalElements: 0
  });

  
  const handleSubmit = (filtro: ConsultaLogsForm) => {
    handlePage(null);
  };

  const handlePage = (event: DataTablePageParams | null) => {
    setLoading(true);
    service.find(filtro?.tipo, event?.page, event?.rows)
      .then(result => setLogs({...result, first: event?.first || 1}))
      .finally(() => setLoading(false));
  }

    const { handleSubmit: formikSubmit, values: filtro, handleChange} = useFormik<ConsultaLogsForm>({
    onSubmit: handleSubmit,
    initialValues: { tipo: '' },
  });

  return (
    <Layout titulo="Logs">
      <form onSubmit={formikSubmit}>
        <div className="columns">
          <Input
            id="tipo"
            name="tipo"
            label="Tipo"
            value={filtro.tipo}
            onChange={handleChange}
            columnClasses="is-half"
          />
          <div className="field column is-half is-grouped" style={{ marginLeft: 30, marginTop: 30}}>
            <div className="control is-link">
              <button type="submit" className="button is-primary">
                Buscar
              </button>
            </div>  
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
            rows={logs.size}
            first={logs.first}
            value={logs.content}
            totalRecords={logs.totalElements}
            emptyMessage="Nenhum registro encontrado"
          > 
            <Column field="id" header="Código" style={{width:'10%'}}/>
            <Column field="data" header="Data" style={{width:'10%'}}/>
            <Column field="hora" header="Hora" style={{width:'10%'}}/>
            <Column field="tipo" header="Tipo" style={{width:'10%'}}/>
            <Column field="descricao" header="Descrição" alignHeader="center" style={{ width: '60%', textAlign: "center" }} />
          </DataTable>
        </div>
      </div>
    </Layout>
  )
}