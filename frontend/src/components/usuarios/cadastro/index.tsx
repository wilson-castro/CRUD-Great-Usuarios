import { useEffect, useState } from "react"
import Router, { useRouter } from "next/router";
import { Usuario } from "../../../app/models/usuarios";
import { Layout } from "../../layout"
import { UsuarioForm } from "./form"
import { useUsuarioService } from "app/services";
import { Alert } from "components/common/message";

function timeout(delay: number) {
  return new Promise( res => setTimeout(res, delay) );
}

export const CadastroUsuarios: React.FC = () => {
  const [usuario, setUsuario] = useState<Usuario>({});
  const [messages, setMessages] = useState<Array<Alert>>([]);
  const service = useUsuarioService();
  const router = useRouter();
  const { id } = router.query;

  useEffect(() => {
    if (id) {
      service.carregarUsuario(id).then(usuarioEncontrado => setUsuario(usuarioEncontrado));
    }
  }, [id]);

  const handleSubmit = (usuario: Usuario) => {

    const successHandler = (message: string) => {
      setMessages([{ tipo: "success", texto: message}]);
    };
    const errorHandler = (error: any) => {
      const { status, message } = error.response.data;
      setMessages([{ tipo: status != 409 ? "error" : "warning", texto: message }]);
    };

    if (usuario.id) {
      service.atualizar(usuario)
        .then(() => successHandler("Usuário atualizado com sucesso!"))
        .catch(errorHandler);
    } else {
      service.salvar(usuario)
        .then(async () => {
          successHandler("Usuário salvo com sucesso!");
          await timeout(1000);  
          Router.push('/consultas/usuarios');
        })
        .catch(errorHandler);
    }
  };

  return (
    <Layout titulo="Cadastro de Usuários" mensagens={messages}>
      <UsuarioForm usuario={usuario} onSubmit={handleSubmit} />
    </Layout>
  )
}