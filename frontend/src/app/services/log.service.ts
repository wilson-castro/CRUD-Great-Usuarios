import { AxiosResponse } from "axios";

import { httpClient } from "app/http";
import { Page } from "app/models/common/page";
import { Log } from "app/models/logs";

const resourceURL: string = "/api/logs";

export const useLogService = () => {
  const find = async (
    tipo: string = '',
    page: number = 0,
    size: number = 5): Promise<Page<Log>> => {

    const url = `${resourceURL}?tipo=${tipo}&page=${page}&size=${size}`;
    const response: AxiosResponse<Page<Log>> = await httpClient.get(url);

    return response.data;
  }
  return {
    find
  };
}
