package com.skilbox.shopdb.vm

import com.skilbox.shopdb.database.Database
import com.skilbox.shopdb.database.tables.*
import timber.log.Timber
import java.time.Instant

class DataBaseRepository {

    private val addressesDao = Database.instance.addressesDao()
    private val departmentsDao = Database.instance.departmentsDao()
    private val employersDao = Database.instance.employersDao()
    private val lincDeptEmpPosDao = Database.instance.lincDeptEmpPosDao()
    private val positionDao = Database.instance.positionDao()
    private val productDao = Database.instance.productDao()
    private val productUnitDao = Database.instance.productUnitDao()
    private val saleDao = Database.instance.saleDao()
    private val saleDetailDao = Database.instance.saleDetailDao()
    private val salePriceDao = Database.instance.salePriceDao()
    private val shopsDao = Database.instance.shopsDao()
    private val suppliersDao = Database.instance.suppliersDao()
    private val suppliersPriceDao = Database.instance.suppliersPriceDao()
    private val supplyDetailDao = Database.instance.supplyDetailDao()
    private val supplyDao = Database.instance.supplyDao()
    private val warehouseDao = Database.instance.warehouseDao()

    suspend fun addAdresses(addresses: List<Addresses>): List<Addresses> {
        Timber.e("addAdresses")

        addressesDao.insertAddresses(addresses = addresses)
        return addressesDao.getAllAddresses()
    }

    suspend fun getAllAddresses(): List<Addresses> {
        return addressesDao.getAllAddresses()
    }

    suspend fun checkAddresses(city: String, street: String, building: String): Int =
        addressesDao.checkAddresses(city, street, building)

    suspend fun deleteAddress(addressId: Long) {
        addressesDao.deleteAddressesById(addressId = addressId)
    }

    suspend fun clianAddressesList() {
        addressesDao.deleteAllAddresses()
    }

// Shops

    suspend fun addShops(shops: List<Shops>) {
        shopsDao.insertShops(shops)
    }

    suspend fun getShopsList(): List<ShopsAndAdresses> {
        return shopsDao.getShopsWhithAddresses()
    }

    suspend fun deleteShop(shopId: Long) {
        shopsDao.deleteShopsById(shopId = shopId)
    }

    suspend fun clianShopList() {
        shopsDao.clianShopList()
    }

    // Departs

    suspend fun addDepart(depatr: List<Departments>) {
        departmentsDao.insertDepartments(depatr)
    }

    suspend fun getShopsWithDepartmentsAndParent(): List<ShopsWithDepartmentsAndParent> {
        return departmentsDao.getShopsWithDepartmentsAndParent()
    }

    suspend fun getShopsWithDepartments(): List<ShopsWithDepartments> {
        return departmentsDao.getShopsWithDepartments()
    }

    suspend fun getShopsAndDeparts(): List<ShopsWithDepartments> {
        return departmentsDao.getShopsAndDeparts()
    }

    suspend fun getShopsAndDepartsFilter(shopId: Long): List<ShopsWithDepartments> {
        return departmentsDao.getShopsAndDepartsFilter(shopId)
    }

    suspend fun deleteDepartmentsById(deptId: Long) {
        departmentsDao.deleteDepartmentsById(deptId)
    }
// position

    suspend fun getPositionList(): List<Positions> {
        return positionDao.getAllPositions()
    }

    suspend fun getPositionWhithPlace(): List<PositionWhithPlace> {
        return positionDao.getPositionWhithPlace()
    }

    suspend fun getPositionsById(positionId: Long): Positions {
        return positionDao.getPositionsById(positionId)
    }

    suspend fun addPosition(positions: Positions) {
        positionDao.insertPositions(listOf(positions))
    }

    suspend fun deletePosition(positionId: Long) {
        positionDao.deletePositionsById(positionId)
    }

    suspend fun clianPositionList() {
        positionDao.clianPositionList()
    }

    // employers

    suspend fun getEmploersPositionPlace(): List<EmploersPositionPlace> {
        return employersDao.getEmploersPositionPlace()
    }

    suspend fun cleanEmployerList() {
        employersDao.cleanEmployerList()
    }

    suspend fun deleteEmployerById(empId: Long) {
        employersDao.deleteEmployersById(empId)
    }

    suspend fun addEmployer(employers: Employers) {
        employersDao.insertEmployers(listOf(employers))
    }

    suspend fun addLinc_Dept_Emp_Post(lincDeptEmpPost: Linc_Dept_Emp_Pos) {
        lincDeptEmpPosDao.insertLinc_Dept_Emp_Pos(lincDeptEmpPost)
    }

    suspend fun findEmployer(surname: String, name: String, middle_name: String): Employers {
        return employersDao.findEmployer(surname, name, middle_name)
    }

    suspend fun deleteLincByEmpId(empId: Long) {
        lincDeptEmpPosDao.deleteLincByEmpId(empId)
    }

    suspend fun clianLincList() {
        lincDeptEmpPosDao.clianLincList()
    }

    suspend fun getProductUnit(): List<Products_Unit> {
        return productUnitDao.getAllProducts_Unit()
    }

    suspend fun insertProducts_Unit(productsUnit: List<Products_Unit>) {
        productUnitDao.insertProducts_Unit(productsUnit)
    }

    suspend fun getProductList(): List<ProductsAndUnit> {
        return productDao.getAllProductsAndUnits()
    }

    suspend fun deleteProduct(productId: Long) {
        return productDao.deleteProductsById(productId)
    }

    suspend fun cleanProductList() {
        return productDao.cleanProductList()
    }

    suspend fun addProduct(product: Products) {
        productDao.insertProducts(listOf(product))
    }

    suspend fun geSuppliersList(): List<SuppliersAndAdresses> {
        return suppliersDao.getSuppliersAndAddress()
    }

    suspend fun deleteSuppliersDyId(suppliersId: Long) {
        suppliersDao.deleteSuppliersById(suppliersId)
    }

    suspend fun cleanSuppliesList() {
        suppliersDao.cleanSuppliesList()
    }

    suspend fun addSupplies(suppliers: Suppliers) {
        suppliersDao.insertSuppliers(listOf(suppliers))
    }

    suspend fun getAllSale_Price(): List<SaleWhithProductsPrice> {
        return salePriceDao.getAllSale_Price()
    }

    suspend fun deleteSale_PriceById(salePriceId: Long) {
        salePriceDao.deleteSale_PriceById(salePriceId)
    }

    suspend fun addSalePrice(salePrice: Sale_Price) {
        salePriceDao.insertSale_Price(listOf(salePrice))
    }

    suspend fun clianSale_PriceList() {
        salePriceDao.clianSale_PriceList()
    }

    suspend fun getListSuppliesPriceAndProduct(): List<SuppliersPriceProduct> {
        return suppliersPriceDao.getListSuppliesPriceAndProduct()
    }

    suspend fun deleteSuppliers_PriceById(suppliersPriceId: Long) {
        suppliersPriceDao.deleteSupplies_PriceById(suppliersPriceId)
    }

    suspend fun addSuppliersPrice(suppliersPrice: Supplies_Price) {
        suppliersPriceDao.insertSupplies_Price(listOf(suppliersPrice))
    }

    suspend fun clianSuppliers_PriceList() {
        suppliersPriceDao.clianSupplies_PriceList()
    }

    suspend fun getSupplyDetailAndPrice(): List<SupplyDetailAndPrice> {
        return supplyDao.getSupplyDetailAndPrice()
    }

    suspend fun addSupply(suply: Supply) {
        supplyDao.insertSupply(suply)
    }

    suspend fun getLastSupply(): Long {
        return supplyDao.getLastSupply()
    }

    suspend fun addSupplyDetail(detailValues: List<Supply_Detail>) {
        supplyDetailDao.insertSupply_Detail(detailValues)
    }

    suspend fun clianSupplyDetail() {
        supplyDetailDao.clianSupplyDetail()
    }

    suspend fun clianSupplyList() {
        supplyDao.clianSupplyList()
    }

    suspend fun getTotalSupply(): List<TotalSupply> {
        return supplyDao.getTotalSupply()
    }

    suspend fun getAllWarehouse(): List<Warehouse> {
        return warehouseDao.getAllWarehouse()
    }

    suspend fun addSale(sale: Sale) {
        saleDao.insertSale(listOf(sale))
    }

    suspend fun addWarehouseValue(value: Warehouse) {
        warehouseDao.insertWarehouse(value)
    }

    suspend fun upDateWarehouseByProduct(productId: Long, count_product: Double, date: Instant) {
        warehouseDao.upDateWarehouseByProduct(productId, count_product, date)
    }

    suspend fun getAllProductsInWarehouse(): List<CountsProductsInWarehouse> {
        return warehouseDao.getAllProductsInWarehouse()
    }

    suspend fun getTotalSale(): List<TotalSale> {
        return saleDao.getTotalSale()
    }

    suspend fun getPreoductForSale(): List<NewSale> {
        return saleDao.getPreoductForSale()
    }

    suspend fun getLastSale(): Long {
        return saleDao.getLastSale()
    }

    suspend fun addSaleDetail(saleDetail: List<Sale_Detail>) {
        saleDetailDao.insertSale_Detail(saleDetailId = saleDetail)
    }

    suspend fun deleteSale_DetailBySaleID(saleId: Long) {
        saleDetailDao.deleteSale_DetailBySaleID(saleId)
    }
    suspend fun deleteSale(saleId: Long) {
        saleDao.deleteSaleById(saleId)
    }

    suspend fun clianSaleList() {
        saleDao.clianSaleList()
    }

    suspend fun clianSailDetail() {
        saleDetailDao.clianSaleDetail()
    }
}
