# Checkout


## Task
We are working with a client who wants to launch an e-commerce site to promote their
brand, below are some of the products that they will be listing:

| ID  | Name         | Price  |
| --- | ------------ | ------ |
| 1   | Water Bottle | £24.95 |
| 2   | Hoodie       | £65.00 |
| 3   | Sticker Set  | £3.99  |

## Promotions
The marketing team would like to run the following promotions;

- If you spend over £75 then you get a 10% discount
- If you buy two or more water bottles then the price drops to £22.99 each

Multiple promotions can be applied to the same checkout.

The checkout system needs to be able to scan the items in any order then apply the
promotion rules. These rules should be allowed to change over time.

Implement a checkout that is able to apply these rules.

Do not worry about the storage or retrieval of any items in a database, for the purpose
of this test they only need to be processed.