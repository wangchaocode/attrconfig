# Read Me First
```
1、读取数据库字典配置，更新到自己对应的bean（dictLabel对应bean属性）
2、适用大量配置场景
3、默认赋值（不配置则使用下列的值）：
  dictTableName: sys_dict_data
  dictLabel: dict_label
  dictValue: dict_value
  dictType: dict_type
自己的type值是需要输入的，typeValue: user
```

# Getting Started
引入jar包依赖
```$xslt
<dependency>
    <groupId>com.freeman</groupId>
    <artifactId>attrconfig-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>

```
### Reference Documentation
v1.0版本

例如：
## 数据库数据：
![数据库](https://github.com/wangchaocode/attrconfig/assets/37795817/688e4d08-7cdf-4a43-bd74-13a05d45bf01)
## 自己bean:
![bean](https://github.com/wangchaocode/attrconfig/assets/37795817/ad94bfc4-8238-4f95-af25-835746da1564)
## 通过本starter实现：
![jg](https://github.com/wangchaocode/attrconfig/assets/37795817/b6e18bd6-f58a-43a6-91e2-157414a14558)
## 如有问题可联系QQ：997035623，备注github问题咨询



