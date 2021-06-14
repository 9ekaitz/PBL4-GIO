<?php

// autoload_static.php @generated by Composer

namespace Composer\Autoload;

class ComposerStaticInit92fbfc0aba205fc5bf84dfca02b838dc
{
    public static $prefixLengthsPsr4 = array (
        'g' => 
        array (
            'gio\\' => 4,
        ),
    );

    public static $prefixDirsPsr4 = array (
        'gio\\' => 
        array (
            0 => __DIR__ . '/../..' . '/src',
        ),
    );

    public static $classMap = array (
        'Composer\\InstalledVersions' => __DIR__ . '/..' . '/composer/InstalledVersions.php',
    );

    public static function getInitializer(ClassLoader $loader)
    {
        return \Closure::bind(function () use ($loader) {
            $loader->prefixLengthsPsr4 = ComposerStaticInit92fbfc0aba205fc5bf84dfca02b838dc::$prefixLengthsPsr4;
            $loader->prefixDirsPsr4 = ComposerStaticInit92fbfc0aba205fc5bf84dfca02b838dc::$prefixDirsPsr4;
            $loader->classMap = ComposerStaticInit92fbfc0aba205fc5bf84dfca02b838dc::$classMap;

        }, null, ClassLoader::class);
    }
}
