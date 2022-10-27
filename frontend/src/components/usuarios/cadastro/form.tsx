import { useFormik } from "formik";
import Router from "next/router";
import { Usuario } from "../../../app/models/usuarios";
import { Input, InputCPF, InputDate } from "components/common";
import { validationScheme } from "./validationSchema";

interface UsuarioFormProps {
  usuario: Usuario,
  onSubmit: (usuario: Usuario) => void
}

const formScheme: Usuario = {
  id: '',
  rg: '',
  cpf: '',
  nome: '',
  nomeMae: '',
  dataCadastro: '',
  dataNascimento: '',
}

export const UsuarioForm: React.FC<UsuarioFormProps> = ({ 
  usuario,
  onSubmit
}) => {

  const formik = useFormik<Usuario>({
    initialValues: { ...formScheme, ...usuario  },
    onSubmit,
    enableReinitialize: true,
    validationSchema: validationScheme
  });

  return (
    <form onSubmit={formik.handleSubmit}>

      {
        formik.values.id && (
          <div className="columns">
            <Input
                id="id"
                disabled
                name="id"
                label="ID: *"
                autoComplete="off"
                columnClasses="is-half"
                value={formik.values.id}
                onChange={formik.handleChange}
            />
            <InputDate
                disabled
                id="dataCadastro"
                autoComplete="off"
                name="dataCadastro"
                columnClasses="is-half"
                label="Data de Cadastro: *"
                onChange={formik.handleChange}
                value={formik.values.dataCadastro}
            />
          </div>
        )
      }
      
      <div className="columns">
        <Input
          id="nome"
          name="nome"
          label="Nome: *"
          autoComplete="off" 
          columnClasses="is-full"
          value={formik.values.nome}
          error={formik.errors.nome}
          onChange={formik.handleChange}
        />
      </div>
      <div className="columns">
        <InputCPF
          id="cpf"
          name="cpf"
          label="CPF: *"
          autoComplete="off"
          columnClasses="is-half"
          error={formik.errors.cpf}
          value={formik.values.cpf}
          onChange={formik.handleChange}
          />
        <Input
          id="rg"
          name="rg"
          label="RG: *"
          autoComplete="off"
          columnClasses="is-half"
          error={formik.errors.rg}
          value={formik.values.rg}
          onChange={formik.handleChange}
        />
      </div>
      <div className="columns">
        <Input
          id="nomeMae"
          name="nomeMae"
          autoComplete="off" 
          label="Nome da Mãe: *"
          columnClasses="is-half"
          value={formik.values.nomeMae}
          error={formik.errors.nomeMae}
          onChange={formik.handleChange}
        />
        <InputDate
          autoComplete="off" 
          id="dataNascimento"
          name="dataNascimento"
          columnClasses="is-half"
          label="Data de Nascimento: *"
          onChange={formik.handleChange}
          value={formik.values.dataNascimento}
          error={formik.errors.dataNascimento}
        />
      </div>
      <div className="field is-grouped">
        <div className="control is-link">
            <button type="submit" className="button is-success">
                { formik.values.id ? "Atualizar" : "Salvar" }                        
            </button>
        </div>
        <div className="control">
            <button type="button" 
                    onClick={() => Router.push("/consultas/usuarios")} 
                    className="button">
                Voltar                        
            </button>
        </div>
      </div>          
    </form>
  )
}