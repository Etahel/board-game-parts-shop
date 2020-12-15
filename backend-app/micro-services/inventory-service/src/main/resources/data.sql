DO
$$
    DECLARE
        categoryId      bigint;
        DECLARE stockId bigint;
    BEGIN
        SET SCHEMA 'business';
        INSERT INTO element_categories (name, description, code, version)
        VALUES ('a_title', 'a_desc', 'tst', 0)
        RETURNING id INTO categoryId;
        INSERT INTO stock (available, stock_size, version) VALUES (true, 1000, 0) returning id INTO stockId;
        INSERT INTO elements (name, description, price, category_id, stock_id, version)
        values ('name', 'desc', 200, categoryId, stockId, 0);
    END
$$;