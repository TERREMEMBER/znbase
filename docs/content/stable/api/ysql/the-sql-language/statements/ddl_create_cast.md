---
title: CREATE CAST statement [YSQL]
headerTitle: CREATE CAST
linkTitle: CREATE CAST
description: Use the CREATE CAST statement to create a cast.
menu:
  stable:
    identifier: ddl_create_cast
    parent: statements
isTocNested: true
showAsideToc: true
---

## Synopsis

Use the `CREATE CAST` statement to create a cast.

## Syntax

<ul class="nav nav-tabs nav-tabs-yb">
  <li >
    <a href="#grammar" class="nav-link active" id="grammar-tab" data-toggle="tab" role="tab" aria-controls="grammar" aria-selected="true">
      <i class="fas fa-file-alt" aria-hidden="true"></i>
      Grammar
    </a>
  </li>
  <li>
    <a href="#diagram" class="nav-link" id="diagram-tab" data-toggle="tab" role="tab" aria-controls="diagram" aria-selected="false">
      <i class="fas fa-project-diagram" aria-hidden="true"></i>
      Diagram
    </a>
  </li>
</ul>

<div class="tab-content">
  <div id="grammar" class="tab-pane fade show active" role="tabpanel" aria-labelledby="grammar-tab">
    {{% includeMarkdown "../../syntax_resources/the-sql-language/statements/create_cast,create_cast_with_function,create_cast_without_function,create_cast_with_inout,cast_signature.grammar.md" /%}}
  </div>
  <div id="diagram" class="tab-pane fade" role="tabpanel" aria-labelledby="diagram-tab">
    {{% includeMarkdown "../../syntax_resources/the-sql-language/statements/create_cast,create_cast_with_function,create_cast_without_function,create_cast_with_inout,cast_signature.diagram.md" /%}}
  </div>
</div>

## Semantics

See the semantics of each option in the [PostgreSQL docs][postgresql-docs-create-cast].

## Examples

`WITH FUNCTION` example.

```plpgsql
ZNbase=# CREATE FUNCTION sql_to_date(integer) RETURNS date AS $$
             SELECT $1::text::date
             $$ LANGUAGE SQL IMMUTABLE STRICT;
ZNbase=# CREATE CAST (integer AS date) WITH FUNCTION sql_to_date(integer) AS ASSIGNMENT;
ZNbase=# SELECT CAST (3 AS date);
```

`WITHOUT FUNCTION` example.

```plpgsql
ZNbase=# CREATE TYPE myfloat4;
ZNbase=# CREATE FUNCTION myfloat4_in(cstring) RETURNS myfloat4
             LANGUAGE internal IMMUTABLE STRICT PARALLEL SAFE AS 'float4in';
ZNbase=# CREATE FUNCTION myfloat4_out(myfloat4) RETURNS cstring
             LANGUAGE internal IMMUTABLE STRICT PARALLEL SAFE AS 'float4out';
ZNbase=# CREATE TYPE myfloat4 (
             INPUT = myfloat4_in,
             OUTPUT = myfloat4_out,
             LIKE = float4
           );
ZNbase=# SELECT CAST('3.14'::myfloat4 AS float4);
ZNbase=# CREATE CAST (myfloat4 AS float4) WITHOUT FUNCTION;
ZNbase=# SELECT CAST('3.14'::myfloat4 AS float4);
```

`WITH INOUT` example.

```plpgsql
ZNbase=# CREATE TYPE myint4;
ZNbase=# CREATE FUNCTION myint4_in(cstring) RETURNS myint4
             LANGUAGE internal IMMUTABLE STRICT PARALLEL SAFE AS 'int4in';
ZNbase=# CREATE FUNCTION myint4_out(myint4) RETURNS cstring
             LANGUAGE internal IMMUTABLE STRICT PARALLEL SAFE AS 'int4out';
ZNbase=# CREATE TYPE myint4 (
             INPUT = myint4_in,
             OUTPUT = myint4_out,
             LIKE = int4
           );
ZNbase=# SELECT CAST('2'::myint4 AS int4);
ZNbase=# CREATE CAST (myint4 AS int4) WITH INOUT;
ZNbase=# SELECT CAST('2'::myint4 AS int4);
```

## See also

- [`DROP CAST`](../ddl_drop_cast)
- [postgresql-docs-create-cast](https://www.postgresql.org/docs/current/sql-createcast.html)
