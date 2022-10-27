import Link from "next/link";

export const Menu: React.FC = () => {
    return (
        <aside className="column is-2 is-narrow-mobile is-fullheight section is-hidden-mobile">
            <p className="menu-label is-hidden-touch">
                CRUD Usuários GREAT
            </p>
            <ul className="menu-list">
                <MenuItem href="/consultas/usuarios" label="Listagem"  />
            </ul>
        </aside>
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
            <Link legacyBehavior href={props.href}>
                <a onClick={props.onClick}>
                    <span className="icon"></span> { props.label } 
                </a>
            </Link>
        </li>
    )
}