SUMMARY = "Package wrapper around a minimal Xen guest image"
inherit xen-guest-package

SRC_URI = "file://xen-guest-image-minimal.cfg"

S = "${UNPACKDIR}"

do_install:append() {
  install ${UNPACKDIR}/xen-guest-image-minimal.cfg ${D}${vesseldir}/
}
