import { Injectable, inject, signal } from "@angular/core";
import { Product } from "./product.model";
import { HttpClient } from "@angular/common/http";
import { catchError, Observable, of, tap } from "rxjs";

@Injectable({
  providedIn: "root"
}) export class ProductsService {

  private readonly http = inject(HttpClient);
  private readonly path = "/api/products";

  private readonly _products = signal<Product[]>([]);
  private readonly _totalElements = signal<number>(0);

  public readonly products = this._products.asReadonly();
  public readonly totalElements = this._totalElements.asReadonly();

  public get(params: any): Observable<any> {
    return this.http.get<any>("/api/products", {
      params: {...params}
    }).pipe(
      catchError((error) => {
        return this.http.get<Product[]>("assets/products.json");
      }),
      tap((products) => {
        this._products.set(products["content"])
        this._totalElements.set(products["totalElements"])
        console.log(products)
      }),
    );
  }
  public getNotPaginated(params: any): Observable<any> {
    return this.http.get<any>("/api/products/notp", {
      params: {...params}
    }).pipe(
      catchError((error) => {
        return this.http.get<Product[]>("assets/products.json");
      }),
      tap((products) => {
        this._products.set(products)
        console.log(products)
      }),
    );
  }

  public create(product: Product): Observable<boolean> {
    return this.http.post<boolean>(this.path, product).pipe(
      catchError(() => {
        return of(true);
      }),
      tap(() => this._products.update(products => [product, ...products])),
    );
  }

  public update(product: Product): Observable<boolean> {
    return this.http.patch<boolean>(`${this.path}/${product.id}`, product).pipe(
      catchError(() => {
        return of(true);
      }),
      tap(() => this._products.update(products => {
        return products.map(p => p.id === product.id ? product : p)
      })),
    );
  }

  public delete(productId: number): Observable<boolean> {
    return this.http.delete<boolean>(`${this.path}/${productId}`).pipe(
      catchError(() => {
        return of(true);
      }),
      tap(() => this._products.update(products => products.filter(product => product.id !== productId))),
    );
  }
}
