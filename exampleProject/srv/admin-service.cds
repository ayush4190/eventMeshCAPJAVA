using { sap.capire.products as db } from '../db/schema';

service AdminService {
    entity Products   as projection on db.Products;
   
}