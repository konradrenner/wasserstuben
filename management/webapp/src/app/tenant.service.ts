import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Tenant} from './tenant';

@Injectable({
  providedIn: 'root'
})
export class TenantService {

   private tenant: Tenant = <Tenant>{};

  constructor(private http: HttpClient) { }
  
  load(): Promise<any>{
      return this.http.get<Tenant>('/api/v1/tenant')
      .toPromise()
      .then(dt => this.tenant = dt)
      .catch((err: any) => Promise.resolve());
  }
  
  getTenant(): Tenant{
      return this.tenant;
  }
}
