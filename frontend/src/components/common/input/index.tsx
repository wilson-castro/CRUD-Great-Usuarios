import { InputHTMLAttributes } from 'react'
import { FormatUtils } from '@4us-dev/utils';

const formatUtils = new FormatUtils();

interface InputProps extends InputHTMLAttributes<HTMLInputElement> {
    id: string;
    label: string;
    columnClasses?: string;
    error?: string;
    formatter?: (value: string) => string | undefined;
}

export const cpfMask = (cpf?: string | null | undefined)  => {
    return cpf
        ?.replace(/\D/g, '') 
        ?.replace(/(\d{3})(\d)/, '$1.$2') 
        ?.replace(/(\d{3})(\d)/, '$1.$2')
        ?.replace(/(\d{3})(\d{1,2})/, '$1-$2')
        ?.replace(/(-\d{2})\d+?$/, '$1');
}

export const Input: React.FC<InputProps> = ({
    label,
    columnClasses,
    id,
    error,
    formatter,
    onChange,
    ...inputProps
}: InputProps) => {

    const onInputChange = (event: any) => {
        const value = event.target.value;
        const name = event.target.name;

        const formattedValue = (formatter && formatter(value as string)) || value;

        if (onChange !== undefined) {
                    onChange({
            ...event,
            target: {
                name,
                value: formattedValue                
            }
        })
        }

    }

    return (
        <div className={`field column ${columnClasses}` }>
            <label className="label" htmlFor={id}>{label}</label>
            <div className="control">
                <input className="input" 
                    onChange={onInputChange}
                    id={id} {...inputProps} />
                {error &&
                    <p className="help is-danger">{ error }</p>
                }
            </div>
        </div>
    )
}

export const InputCPF: React.FC<InputProps> = (props: InputProps) => {
    return (
        <Input {...props} formatter={cpfMask} maxLength={14} />
    )
}

export const InputDate: React.FC<InputProps> = (props: InputProps) => {

    const formatData = (value: string) => {
        if(!value){
            return '';
        }

        const data = formatUtils.formatOnlyIntegers(value);
        const size = value.length;

        if(size <= 2){
            return data;
        }

        if(size <= 4){
            return data.substr(0, 2) + "/" + data.substr(2, 2);
        }

        if(size <= 6){
            return data.substr(0,2) + "/" + data.substr(2,2) + "/" + data.substr(4,2)
        }
    }

    return (
        <Input {...props} maxLength={10} formatter={formatData} />
    )
}
