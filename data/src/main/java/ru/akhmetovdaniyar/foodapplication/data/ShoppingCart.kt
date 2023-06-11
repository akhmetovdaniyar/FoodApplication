package ru.akhmetovdaniyar.foodapplication.data

import ru.akhmetovdaniyar.foodapplication.data.dishes.Dish

object ShoppingCart {
    private val dishes = mutableMapOf<Dish, Int>()

    // Добавление товара в корзину
    fun addProduct(dish: Dish) {
        val quantity = dishes[dish] ?: 0
        dishes[dish] = quantity + 1
    }

    // Удаление товара из корзины
    fun removeProduct(dish: Dish) {
        val quantity = dishes[dish] ?: return
        if (quantity > 1) {
            dishes[dish] = quantity - 1
        } else {
            dishes.remove(dish)
        }
    }

    // Получение общей стоимости товаров в корзине
    fun getTotalPrice(): Int {
        var totalPrice = 0
        for ((dish, quantity) in dishes) {
            totalPrice += dish.price * quantity
        }
        return totalPrice
    }

    // Получение количества определенного товара в корзине по его названию
    fun getProductQuantity(dishName: String): Int {
        val dish = dishes.keys.find { it.name == dishName }
        return dishes[dish] ?: 0
    }

    // Получение всех товаров в корзине
    fun getAllProducts(): MutableList<Dish> {
        return dishes.keys.toMutableList()
    }
}