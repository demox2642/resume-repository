package com.skilbox.shopdb.vm

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.withTransaction
import com.skilbox.shopdb.database.Database
import com.skilbox.shopdb.database.tables.*
import kotlinx.coroutines.*
import timber.log.Timber
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@SuppressLint("LogNotTimber")
class DataBaseViewModel : ViewModel() {

    private val repository = DataBaseRepository()

    private val addressesLiveData = MutableLiveData<List<Addresses>>()
    val addressesLiveDataForFragment: LiveData<List<Addresses>>
        get() = addressesLiveData

    // shop
    private val shopsLiveData = MutableLiveData<List<ShopsAndAdresses>>()
    val shopsLiveDataForFragment: LiveData<List<ShopsAndAdresses>>
        get() = shopsLiveData

    // depart
    private val departLiveData = MutableLiveData<List<ShopsWithDepartmentsAndParent>>()
    val deparrtLiveDataForFragment: LiveData<List<ShopsWithDepartmentsAndParent>>
        get() = departLiveData

    private val shopsAndDepartLiveData = MutableLiveData<List<ShopsWithDepartments>>()
    val shopsAndDepartForFragment: LiveData<List<ShopsWithDepartments>>
        get() = shopsAndDepartLiveData

    private val itemsForParentSpinner = MutableLiveData<List<String>>()

    // positions
    private val positionLiveData = MutableLiveData<List<PositionWhithPlace>>()
    val positionsForFragment: LiveData<List<PositionWhithPlace>>
        get() = positionLiveData

    private val positionAfterFilterLD = MutableLiveData<Positions>()
    val positionAfterFilterForFragment: LiveData<Positions>
        get() = positionAfterFilterLD

    // employers
    private val emploersPositionPlaceLiveData = MutableLiveData<List<EmploersPositionPlace>>()
    val emploersPositionPlaceForFragment: LiveData<List<EmploersPositionPlace>>
        get() = emploersPositionPlaceLiveData

    private val employerLiveData = MutableLiveData<Employers>()
    val employerForFragment: LiveData<Employers>
        get() = employerLiveData

    private val productsUnitLiveData = MutableLiveData<List<Products_Unit>>()
    val productsUnitForFragment: LiveData<List<Products_Unit>>
        get() = productsUnitLiveData

    private val productsListLD = MutableLiveData<List<ProductsAndUnit>>()
    val productsListForFragments: LiveData<List<ProductsAndUnit>>
        get() = productsListLD

    private val suppliersListLD = MutableLiveData<List<SuppliersAndAdresses>>()
    val suppliersListForFragment: LiveData<List<SuppliersAndAdresses>>
        get() = suppliersListLD

    private val salePriceLiveData = MutableLiveData<List<SaleWhithProductsPrice>>()
    val salePriceForFragment: LiveData<List<SaleWhithProductsPrice>>
        get() = salePriceLiveData

    private val listSuppliesPriceAndProductLD = MutableLiveData<List<SuppliersPriceProduct>>()
    val listSuppliesPriceAndProductForFrag: LiveData<List<SuppliersPriceProduct>>
        get() = listSuppliesPriceAndProductLD

    // SupplyDetailAndPrice
    private val supplyDetailAndPriceLD = MutableLiveData<List<SupplyDetailAndPrice>>()
    val supplyDetailAndPriceForFragment: LiveData<List<SupplyDetailAndPrice>>
        get() = supplyDetailAndPriceLD

    //   tempProductCountInSupply
    private var tempProductCountList = listOf<ProductCountInSupply>()
    private val tempProductCountInSupplyLD = MutableLiveData<List<ProductCountInSupply>>(
        tempProductCountList
    )
    val tempProductCountInSupply: LiveData<List<ProductCountInSupply>>
        get() = tempProductCountInSupplyLD

    private val totalSupplyLiveData = MutableLiveData<List<TotalSupply>>()
    val totalSupplyForFragment: LiveData<List<TotalSupply>>
        get() = totalSupplyLiveData

    private val productsInWarehouseLD = MutableLiveData<List<CountsProductsInWarehouse>>()
    val productsInWarehouseForFragment: LiveData<List<CountsProductsInWarehouse>>
        get() = productsInWarehouseLD

    private val totalSaleLiveData = MutableLiveData<List<TotalSale>>()
    val totalSaleForFragment: LiveData<List<TotalSale>>
        get() = totalSaleLiveData

    private val productsForSaleLD = MutableLiveData <List<NewSale>>()
    val productsForSale: LiveData<List<NewSale>>
        get() = productsForSaleLD

    private var tempProductCountListForSale = listOf<NewSale>()
    private val tempProductCountInSaleLD = MutableLiveData<List<NewSale>>(
        tempProductCountListForSale
    )
    val tempProductCountInSale: LiveData<List<NewSale>>
        get() = tempProductCountInSaleLD

    fun addAddressesList(addresses: List<Addresses>) {
        Timber.e("addAddressesList")

        viewModelScope.launch {
            Database.instance.withTransaction {
                for (i in addresses.indices) {
                    if (repository.checkAddresses(
                            addresses[i].city, addresses[i].street, addresses[i].building
                        ) > 0
                    ) {
                        Timber.e("Error add Addresses: ${addresses[i]} is not add")
                        Log.e("Error add Addresses: ", "${addresses[i]} is not add")
                    } else {

                        try {
                            addressesLiveData.postValue(repository.addAdresses(listOf(addresses[i])))
                        } catch (t: Throwable) {
                            Timber.e(t)
                        }
                    }
                }

                getAllAddresses()
            }
        }
    }

    fun getAllAddresses() {
        viewModelScope.launch {
            val addresses = repository.getAllAddresses()
            addressesLiveData.postValue(addresses)
        }
    }

    fun deleteAddresses(addressId: Long) {
        viewModelScope.launch {
            repository.deleteAddress(addressId)
        }
        getAllAddresses()
    }

    fun clianList() {
        viewModelScope.launch {
            repository.clianAddressesList()
        }
        getAllAddresses()
    }

    // Shops

    fun addShop(name: String, addressesId: Long) {
        viewModelScope.launch {
            Database.instance.withTransaction {

                try {
                    val shops = listOf(Shops(null, address_id = addressesId, name = name))

                    repository.addShops(shops)
                } catch (t: Throwable) {
                    Log.e("addShop", "$t")
                }
            }
        }
        getShopsList()
    }

    fun getShopsList() {

        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    val shops = repository.getShopsList()
                    Log.e("getShopsList", "$shops")
                    shopsLiveData.postValue(shops)
                } catch (t: Throwable) {
                    Log.e("getShopsList", "$t")
                }
            }
        }
    }

    fun deleteShop(shopId: Long) {
        viewModelScope.launch {
            Database.instance.withTransaction {
                repository.deleteShop(shopId)
            }
        }
        getShopsList()
    }

    fun clianShopList() {
        viewModelScope.launch {
            Database.instance.withTransaction {
                repository.clianShopList()
            }
        }
    }

    // Departments

    fun getDepartList() {
        Log.e("getDeptList", "start")
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {

                    val depts = repository.getShopsWithDepartments()
                    val shopsDeptParents = repository.getShopsWithDepartmentsAndParent()
                    //  val depts=repository.getDepartmentsList()
                    Log.e("getDeptList", " \n depts=$depts \n shopsDeptParents=$shopsDeptParents")
                    departLiveData.postValue(shopsDeptParents)
                } catch (t: Throwable) {
                    Log.e("getDepartList", "$t")
                }
            }
        }
    }

    fun addDepart(parentId: Long?, shopId: Long, full_name: String, short_name: String) {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    val newDepart = Departments(null, parentId, shopId, full_name, short_name)
                    repository.addDepart(listOf(newDepart))
                } catch (t: Throwable) {
                    Timber.e(t)
                }

                getDepartList()
            }
        }
    }

    fun getShopsAndDeparts() {
        viewModelScope.launch {
            Database.instance.withTransaction {
                val departs = repository.getShopsAndDeparts()
                Log.e("getShopsAndDepartsFilter", "$departs")
                shopsAndDepartLiveData.postValue(departs)
            }
        }
    }

    fun getShopsAndDepartsFilter(shopId: Long) {
        viewModelScope.launch {
            Database.instance.withTransaction {
                val departs = repository.getShopsAndDepartsFilter(shopId)
                Log.e("getShopsAndDepartsFilter", "$departs")
                shopsAndDepartLiveData.postValue(departs)
                getItemsForSpinner()
            }
        }
    }

    fun getItemsForSpinner(): LiveData<List<String>> {
        val fatherdepart = shopsAndDepartForFragment.value
        val fatherdepartForSpiner = mutableListOf("")

        for (element in fatherdepart!!) {
            fatherdepartForSpiner.add("отдел: ${element.departments.full_name} магазин: ${element.shop.name} ")
        }
        itemsForParentSpinner.postValue(fatherdepartForSpiner)
        return itemsForParentSpinner
    }

    fun deleteDepartmentsById(deptId: Long) {
        viewModelScope.launch {
            Database.instance.withTransaction {
                repository.deleteDepartmentsById(deptId)
                getDepartList()
            }
        }
    }

    // positions

    fun getPositionWhithPlace() {
        viewModelScope.launch {
            Database.instance.withTransaction {
                val positions = repository.getPositionWhithPlace()
                Log.e("Positions", "$positions")
                positionLiveData.postValue(positions)
            }
        }
    }

    fun addPosition(positionsName: String, departId: Long) {
        viewModelScope.launch {
            Database.instance.withTransaction {
                val positions = Positions(null, departId, positionsName)
                repository.addPosition(positions)
                getPositionWhithPlace()
            }
        }
    }

    fun deletePosition(positionId: Long) {
        viewModelScope.launch {
            Database.instance.withTransaction {
                repository.deletePosition(positionId)
                getPositionWhithPlace()
            }
        }
    }

    fun clianPositionList() {
        viewModelScope.launch {
            Database.instance.withTransaction {
                repository.clianPositionList()
            }
        }
    }

    fun getPositionList(): List<String> {

        val positionList = mutableListOf<String>()

        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    val positionDb = repository.getPositionList()
                    for (i in positionDb.indices) {
                        if (positionList.filter { it == positionDb[i].name }.isEmpty()) {
                            positionList.add(positionDb[i].name)
                        }
                    }
                } catch (t: Throwable) {
                    Log.e("getEmploersPositionPlace", " Error: $t")
                }
            }
        }
        return positionList
    }

    // employers
    fun getEmploersPositionPlace() {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    val employers = repository.getEmploersPositionPlace()
                    Log.e("getEmploersPositionPlace", "$employers")
                    emploersPositionPlaceLiveData.postValue(employers)
                } catch (t: Throwable) {
                    Log.e("getEmploersPositionPlace", " Error: $t")
                    emploersPositionPlaceLiveData.postValue(mutableListOf())
                }
            }
        }
    }

    fun cleanEmployerList() {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    repository.clianLincList()
                    repository.cleanEmployerList()
                } catch (t: Throwable) {
                    Log.e("cleanEmployerList", " Error: $t")
                }
            }
        }
    }

    fun deleteEmployerById(empId: Long) {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    repository.deleteLincByEmpId(empId)
                    repository.deleteEmployerById(empId)
                } catch (t: Throwable) {
                    Log.e("deleteEmployerById", " Error: $t")
                }

                getEmploersPositionPlace()
            }
        }
    }

    fun addEmpAndLincPositionDept(
        emp: Employers,
        deptId: Long,
        position_id: Long,
        date_start: Instant

    ) {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    repository.addEmployer(emp)

                    val employee = repository.findEmployer(
                        surname = emp.surname,
                        name = emp.name,
                        middle_name = emp.middle_name
                    )

                    val lincDeptEmpPos = Linc_Dept_Emp_Pos(
                        null, deptId, position_id,
                        employee.id!!, date_start, null
                    )
                    repository.addLinc_Dept_Emp_Post(lincDeptEmpPos)
                } catch (t: Throwable) {
                    Log.e("addEmpAndLincPositionDept2", " Error: $t")
                }
            }
        }
    }

    fun getPositionsById(positionId: Long) {

        viewModelScope.launch {
            Database.instance.withTransaction {
                try {

                    positionAfterFilterLD.postValue(repository.getPositionsById(positionId))
                    Log.e("getPositionsById", " position2: ${positionAfterFilterForFragment.value}")
                } catch (t: Throwable) {
                    Log.e("getPositionsById", " Error: $t")
                }
            }
        }
    }

    fun findEmployer(surname: String, name: String, middle_name: String) {

        viewModelScope.launch {
            Database.instance.withTransaction {
                try {

                    employerLiveData.postValue(repository.findEmployer(surname, name, middle_name))
                    Log.e("findEmployer", " Error: ${employerLiveData.value}")
                } catch (t: Throwable) {
                    Log.e("findEmployer", " Error: $t")
                }
            }
        }
    }

    fun getProductUnit() {

        viewModelScope.launch {
            Database.instance.withTransaction {
                val productsUnit = repository.getProductUnit()
                productsUnitLiveData.postValue(productsUnit)
            }
        }
    }

    fun insertProducts_Unit() {

        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    getProductUnit()
                    val oldproductsList = productsUnitLiveData.value
                    val productsList = listOf(
                        Products_Unit(1, "килограмм", "кг"),
                        Products_Unit(2, "штук", "шт"),
                        Products_Unit(3, "литров", "л")
                    )
                    val newproductsList = if (oldproductsList!!.isEmpty()) {
                        productsList
                    } else {

                        oldproductsList.filterIndexed { i, values ->
                            productsList[i] != values
                        }
                    }

                    repository.insertProducts_Unit(newproductsList)
                } catch (t: Throwable) {
                    Log.e("insertProducts_Unit", " Error: $t")
                }
            }
        }
    }

    fun getProductList() {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    productsListLD.postValue(repository.getProductList())
                } catch (t: Throwable) {
                    Log.e("getProductList", " Error: $t")
                }
            }
        }
    }

    fun deleteProduct(productId: Long) {
        viewModelScope.launch {
            Database.instance.withTransaction {
                repository.deleteProduct(productId)
                getProductList()
            }
        }
    }

    fun cleanProductList() {
        viewModelScope.launch {
            Database.instance.withTransaction {
                repository.cleanProductList()
                getProductList()
            }
        }
    }

    fun addProduct(product: Products) {
        viewModelScope.launch {
            Database.instance.withTransaction {

                try {
                    repository.addProduct(product)
                } catch (t: Throwable) {
                    Log.e("addProduct", " Error: $t")
                }
                getProductList()
            }
        }
    }

    fun geSuppliersList() {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    suppliersListLD.postValue(repository.geSuppliersList())
                } catch (t: Throwable) {
                    Log.e("geSuppliersList", " Error: $t")
                }
            }
        }
    }

    fun cleanSuppliesList() {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    repository.cleanSuppliesList()
                } catch (t: Throwable) {
                    Log.e("cleanSuppliesList", " Error: $t")
                }
                geSuppliersList()
            }
        }
    }

    fun addSupplies(suppliers: Suppliers) {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    repository.addSupplies(suppliers)
                } catch (t: Throwable) {
                    Log.e("addSupplies", " Error: $t")
                }
                geSuppliersList()
            }
        }
    }

    fun getAllSale_Price() {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    salePriceLiveData.postValue(repository.getAllSale_Price())
                } catch (t: Throwable) {
                    Log.e("getAllSale_Price", " Error: $t")
                }
            }
        }
    }

    fun deleteSale_PriceById(salePriceId: Long) {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    repository.deleteSale_PriceById(salePriceId)
                } catch (t: Throwable) {
                    Log.e("deleteSale_PriceById", " Error: $t")
                }

                getAllSale_Price()
            }
        }
    }

    fun addSalePrice(salePrice: Sale_Price) {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    repository.addSalePrice(salePrice)
                } catch (t: Throwable) {
                    Log.e("addSalePrice", " Error: $t")
                }

                getAllSale_Price()
            }
        }
    }

    fun clianSale_PriceList() {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    repository.clianSale_PriceList()
                } catch (t: Throwable) {
                    Log.e("clianSale_PriceList", " Error: $t")
                }
                getAllSale_Price()
            }
        }
    }

    fun getListSuppliesPriceAndProduct() {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    listSuppliesPriceAndProductLD.postValue(repository.getListSuppliesPriceAndProduct())
                } catch (t: Throwable) {
                    Log.e("getListSuppliesPriceAndProduct", " Error: $t")
                }
            }
        }
    }

    fun deleteSuppliers_PriceById(suppliersPriceId: Long) {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    repository.deleteSuppliers_PriceById(suppliersPriceId)
                } catch (t: Throwable) {
                    Log.e("deleteSuppliers_PriceById", " Error: $t")
                }
                getListSuppliesPriceAndProduct()
            }
        }
    }

    fun clianSuppliers_PriceList() {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    repository.clianSuppliers_PriceList()
                } catch (t: Throwable) {
                    Log.e("clianSuppliers_PriceList", " Error: $t")
                }
                getListSuppliesPriceAndProduct()
            }
        }
    }

    fun addSuppliersPrice(suppliersPrice: Supplies_Price) {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    repository.addSuppliersPrice(suppliersPrice)
                } catch (t: Throwable) {
                    Log.e("clianSuppliers_PriceList", " Error: $t")
                }
                getListSuppliesPriceAndProduct()
            }
        }
    }

    fun getSupplyDetailAndPrice() {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    supplyDetailAndPriceLD.postValue(repository.getSupplyDetailAndPrice())
                } catch (t: Throwable) {
                    Log.e("getSupplyDetailAndPrice", " Error: $t")
                }
            }
        }
    }

    // tempSuppliersPriceProduct
    fun addtempProductCountInSupplyLD(tempProductCount: ProductCountInSupply) {

        val oldtempSuppliersPriceProductLD = tempProductCountInSupplyLD.value?.toMutableList()

        if (oldtempSuppliersPriceProductLD?.filter { it.id == tempProductCount.id }!!.isEmpty()) {
            oldtempSuppliersPriceProductLD.add(tempProductCount)
        }
        tempProductCountInSupplyLD.postValue(oldtempSuppliersPriceProductLD.orEmpty())
    }

    fun addSupply(supply: Supply, detailValues: List<ProductCountInSupply>) {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    repository.addSupply(supply)

                    val warehouseValue = repository.getAllWarehouse()

                    val newWarehouseValue = mutableListOf<Warehouse>()

                    var lastSupplyID = repository.getLastSupply()
                    while (false) {
                        lastSupplyID = repository.getLastSupply()
                    }

                    val detailNewValues = mutableListOf<Supply_Detail>()

                    for (i in detailValues.indices) {
                        detailNewValues.add(
                            Supply_Detail(
                                null, lastSupplyID, detailValues[i].id,
                                detailValues[i].product_quantity.toDouble()
                            )
                        )

                        newWarehouseValue.add(
                            Warehouse(
                                null, detailValues[i].product_id,
                                detailValues[i].product_quantity.toDouble(),
                                LocalDateTime.now().toInstant(ZoneOffset.UTC)
                            )
                        )
                    }

                    repository.addSupplyDetail(detailNewValues)

                    for (i in 0 until newWarehouseValue.size) {
                        if (warehouseValue.filter { it.product_id == newWarehouseValue[i].product_id }.isEmpty()) {

                            repository.addWarehouseValue(newWarehouseValue[i])
                        } else {

                            val new_count_product = newWarehouseValue[i].count + warehouseValue.filter { it.product_id == newWarehouseValue[i].product_id }[0].count
                            repository.upDateWarehouseByProduct(newWarehouseValue[i].product_id, new_count_product, LocalDateTime.now().toInstant(ZoneOffset.UTC))
                        }
                    }
                } catch (t: Throwable) {
                    Log.e("addSupply", " Error: $t")
                } finally {
                    tempProductCountInSupplyLD.postValue(emptyList())
                }
            }
        }
    }

    fun clianSupplyDetail() {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    repository.clianSupplyDetail()
                } catch (t: Throwable) {
                    Log.e("clianSupplyDetail", " Error: $t")
                }
            }
        }
    }

    fun clianSupplyList() {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    repository.clianSupplyList()
                } catch (t: Throwable) {
                    Log.e("clianSupplyList", " Error: $t")
                }
            }
        }
    }

    fun getTotalSupply() {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    totalSupplyLiveData.postValue(repository.getTotalSupply())
                } catch (t: Throwable) {
                    Log.e("getTotalSupply", " Error: $t")
                }
            }
        }
    }

    fun getAllProductsInWarehouse() {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    productsInWarehouseLD.postValue(repository.getAllProductsInWarehouse())
                } catch (t: Throwable) {
                    Log.e("getAllProductsInWarehouse", " Error: $t")
                }
            }
        }
    }

    fun getTotalSale() {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    totalSaleLiveData.postValue(repository.getTotalSale())
                } catch (t: Throwable) {
                    Log.e("getTotalSale", " Error: $t")
                }
            }
        }
    }

    fun getPreoductForSale() {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    productsForSaleLD.postValue(repository.getPreoductForSale())
                } catch (t: Throwable) {
                    Log.e("getPreoductForSale", " Error: $t")
                }
            }
        }
    }

    fun addTempProductCountListForSale(sale: NewSale) {
        val newSals = mutableListOf<NewSale>()
        newSals.addAll(tempProductCountInSaleLD.value!!)
        newSals.add(sale)
        tempProductCountInSaleLD.postValue(newSals)
    }

    fun addSale(sale: Sale, detailValues: List<NewSale>) {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    repository.addSale(sale)

                    val oldValueInWarehouse = repository.getAllWarehouse().toMutableList()

                    val newSaleDetail = mutableListOf<Sale_Detail>()

                    val saleId = repository.getLastSale()

                    for (i in detailValues.indices) {
                        newSaleDetail.add(Sale_Detail(null, saleId, detailValues[i].price_id, detailValues[i].count_product.toDouble()))

                        val totalProductCount = oldValueInWarehouse.filter { it.product_id == detailValues[i].product_id }[0].count - detailValues[i].count_product

                        repository.upDateWarehouseByProduct(productId = detailValues[i].product_id, count_product = totalProductCount, date = sale.date)
                    }

                    repository.addSaleDetail(newSaleDetail)
                } catch (t: Throwable) {
                    Log.e("getPreoductForSale", " Error: $t")
                }
            }
        }
    }

    fun deleteSaile(saleId: Long) {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    repository.deleteSale_DetailBySaleID(saleId)
                    repository.deleteSale(saleId)
                } catch (t: Throwable) {
                    Log.e("deleteSaile", " Error: $t")
                }
            }
        }
    }

    fun clianSaleList() {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    repository.clianSailDetail()
                    repository.clianSaleList()
                } catch (t: Throwable) {
                    Log.e("clianSaleList", " Error: $t")
                }
            }
        }
    }

    fun deleteSuppliersDyId(suppliersId: Long) {
        viewModelScope.launch {
            Database.instance.withTransaction {
                try {
                    repository.deleteSuppliersDyId(suppliersId)
                } catch (t: Throwable) {
                    Log.e("deleteSuppliersDyId", " Error: $t")
                }
            }
        }
    }
}
