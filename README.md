[![Clojars Project](https://img.shields.io/clojars/v/proud/boot-template.svg)](https://clojars.org/proud/boot-template)

# proud

A Boot template for reactive stream based web applications.

Used libraries:

- [Rum][1] as React.js wrapper
- [Rum-MDL][2] as Material Design Lite wrapper
- [Potok][3] for managing application state with reactive stream

## Generate

### Development

Clone this repository. Change whatever you want. Then run:

```
> boot build
```

It will install new template in your maven repository. Use it with:

```
> boot new -t proud -n fresh-new-project
```

### Clojars

Just run:

```
> boot -d boot/new new -t proud -n fresh-new-project
```

## Use

In the `fresh-new-project` run:

```
> boot dev
```

or

```
> boot auto-test
```

## License

Copyright © 2017 LastStar.eu

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

[1]: https://github.com/tonsky/rum
[2]: https://github.com/aJchemist/rum-mdl
[3]: https://github.com/funcool/potok
