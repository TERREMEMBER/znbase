---
title: DROP TYPE statement [YSQL]
headerTitle: DROP TYPE
linkTitle: DROP TYPE
description: Use the DROP TYPE statement to remove a user-defined type from the database.
menu:
  stable:
    identifier: ddl_drop_type
    parent: statements
isTocNested: true
showAsideToc: true
---

## Synopsis

Use the `DROP TYPE` statement to remove a user-defined type from the database.

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
    {{% includeMarkdown "../../syntax_resources/the-sql-language/statements/drop_type.grammar.md" /%}}
  </div>
  <div id="diagram" class="tab-pane fade" role="tabpanel" aria-labelledby="diagram-tab">
    {{% includeMarkdown "../../syntax_resources/the-sql-language/statements/drop_type.diagram.md" /%}}
  </div>
</div>

## Semantics

### *drop_type*

### *type_name*

Specify the name of the user-defined type to drop.

## Examples

Simple example

```plpgsql
ZNbase=# CREATE TYPE feature_struct AS (id INTEGER, name TEXT);
ZNbase=# DROP TYPE feature_struct;
```

`IF EXISTS` example

```plpgsql
ZNbase=# DROP TYPE IF EXISTS feature_shell;
```

`CASCADE` example

```plpgsql
ZNbase=# CREATE TYPE feature_enum AS ENUM ('one', 'two', 'three');
ZNbase=# CREATE TABLE feature_tab_enum (feature_col feature_enum);
ZNbase=# DROP TYPE feature_tab_enum CASCADE;
```

`RESTRICT` example

```plpgsql
ZNbase=# CREATE TYPE feature_range AS RANGE (subtype=INTEGER);
ZNbase=# CREATE TABLE feature_tab_range (feature_col feature_range);
ZNbase=# -- The following should error:
ZNbase=# DROP TYPE feature_range RESTRICT;
ZNbase=# DROP TABLE feature_tab_range;
ZNbase=# DROP TYPE feature_range RESTRICT;
```

## See also

- [`CREATE TYPE`](../ddl_create_type)
