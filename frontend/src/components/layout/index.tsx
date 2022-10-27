import Link from "next/link";
import { ReactNode } from "react";
import { Alert, Message } from "../common/message";
import { Menu } from "../menu";

interface LayoutProps {
    titulo?: string; 
    children?: ReactNode;
    mensagens?: Array<Alert>;
}

export const Layout: React.FC<LayoutProps> = (props: LayoutProps) => {
  return (
        <div className="app">
          <section className="main-content columns is-fullheight">
                <Menu />

                <div className="container column is-10">
                    <div className="section">
                        <div className="card">
                            <div className="card-header">
                                <p className="card-header-title">
                                    {props.titulo}
                                </p>
                            </div>
                            <div className="card-content">
                                <div className="content">
                                    {props.mensagens && 
                                        props.mensagens.map( msg => <Message key={msg.texto} {...msg}  />)
                                    }
                                    
                                    {props.children}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
  )
}

interface MenuItemProps {
    href: string;
    label: string;
    onClick?: () => void;
}

const MenuItem: React.FC<MenuItemProps> = (props: MenuItemProps) => {
    return (
        <li>
            <Link href={props.href}>
                <a onClick={props.onClick}>
                    <span className="icon"></span> { props.label } 
                </a>
            </Link>
        </li>
    )
}