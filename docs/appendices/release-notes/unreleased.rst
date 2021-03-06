==================
Unreleased Changes
==================

.. NOTE::

    These changes have not yet been released.

    If you are viewing this document on the Crate.io website, these changes
    reflect what exists on `the master branch`_ in Git. This is where we
    collect changes before they are ready for release.

.. WARNING::

    Unreleased changes may not be ready for general use and could lead to data
    corruption or data loss. You should `back up your data`_ before
    experimenting with unreleased changes.

.. _the master branch: https://github.com/crate/crate
.. _back up your data: https://crate.io/docs/crate/reference/en/latest/admin/snapshots.html

.. DEVELOPER README
.. ================

.. Changes should be recorded here as you are developing CrateDB. When a new
.. release is being cut, changes will be moved to the appropriate release notes
.. file.

.. When resetting this file during a release, leave the headers in place, but
.. add a single paragraph to each section with the word "None".

.. Always cluster items into bigger topics. Link to the documentation whenever feasible.
.. Remember to give the right level of information: Users should understand
.. the impact of the change without going into the depth of tech.

.. rubric:: Table of contents

.. contents::
   :local:


Breaking Changes
================

None


Deprecations
============

None

Changes
=======

- Relicensed all enterprise features under the Apache License 2.0 and removed
  licensing related code. The ``SET LICENSE`` statement can still be used, but
  it won't have any effect.

- Added an empty ``pg_tablespace`` table in the ``pg_catalog`` schema for
  improved support for PostgreSQL tools.

- Improved the error messages for cast errors for values of type ``object``.

- Added support for the :ref:`CREATE TABLE AS <ref-create-table-as>` statement.

Fixes
=====

- Fixed shard allocation on downgraded nodes where only the ``HOTFIX`` version
  part differs to fully support rolling downgrades to same ``MAJOR.MINOR``
  versions.
